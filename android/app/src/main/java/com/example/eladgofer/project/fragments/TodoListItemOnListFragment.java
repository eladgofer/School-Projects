package com.example.eladgofer.project.fragments;


import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.eladgofer.project.R;
import com.example.eladgofer.project.utils.TodoList;
import com.example.eladgofer.project.utils.TodoListItem;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class TodoListItemOnListFragment extends Fragment {

    @BindView(R.id.rvTodoListItem)
    RecyclerView rvTodoListItem;
    @BindView(R.id.etItem)
    EditText etItem;
    @BindView(R.id.fabAdd)
    FloatingActionButton fabAdd;
    Unbinder unbinder;
    TodoList list;

    public static TodoListItemOnListFragment newInstance(TodoList list) {

        Bundle args = new Bundle();
        args.putParcelable("list", list);
        TodoListItemOnListFragment fragment = new TodoListItemOnListFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_todo_list_item_on_list, container, false);
        list = getArguments().getParcelable("list");
        rvTodoListItem = (RecyclerView) view.findViewById(R.id.rvTodoListItem);
        rvTodoListItem.setLayoutManager(new LinearLayoutManager(getContext()));
        DatabaseReference ref = FirebaseDatabase.
                getInstance().
                getReference("TodoItems").
                child(list.getListId());
        rvTodoListItem.setAdapter(new ProductAdapter(ref));
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick(R.id.fabAdd)
    public void onViewClicked() {
        if (etItem.getText().length() < 1) {
            etItem.setError("Can't be empty.");
            return;
        }

        //1) ref to the table with a new row:
        DatabaseReference newItemRef = FirebaseDatabase.getInstance().
                getReference("TodoItems").
                child(list.getListId()).push();
        FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();

        TodoListItem item = new TodoListItem(etItem.getText().toString(),
                currentUser.getUid(),
                newItemRef.getKey(),
                list.getListId(),
                false);
        newItemRef.setValue(item);
        etItem.setText(null);

    }


    public static class ProductAdapter extends FirebaseRecyclerAdapter<TodoListItem, ProductAdapter.ProductViewHolder> {

        public ProductAdapter(Query query) {
            super(TodoListItem.class,
                    R.layout.todo_list_product_item,
                    ProductViewHolder.class,
                    query);
        }

        @Override
        protected void populateViewHolder(final ProductViewHolder viewHolder, final TodoListItem model, int position) {
            viewHolder.tvProduct.setText(model.getName());

            viewHolder.fabDeleteItem.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String itemId = model.getTodoItemId();
                    String listId = model.getTodoListId();
                    FirebaseDatabase.getInstance().getReference("TodoItems").child(listId).child(itemId).setValue(null);
                }
            });

        }

        public static class ProductViewHolder extends RecyclerView.ViewHolder {
            TextView tvProduct, tvItemId;
            ImageView ivIcon;
            TodoListItem model;
            Button fabDeleteItem;

            public ProductViewHolder(View itemView) {
                super(itemView);

                tvProduct = (TextView) itemView.findViewById(R.id.tvProduct);

                ivIcon = (ImageView) itemView.findViewById(R.id.ivItemIcon);
                fabDeleteItem = (Button) itemView.findViewById(R.id.fabDeleteItem);

            }
        }
    }
}
