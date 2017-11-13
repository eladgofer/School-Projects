package com.example.eladgofer.project.fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.eladgofer.project.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class PhraseFragment extends Fragment {


    TextView tvPhrase;
    FloatingActionButton fabShare;
    String[] phrases = {"Going to church doesn’t make you a Christian, anymore than standing in your garage makes you a car.",
            "I moaned because I had no shoes, until I met a man who had no feet.",
            "In the end, we will remember not the words of our enemies, but the silence of our friends.",
            "Keep smiling… It makes people wonder what you’ve been up to.",
            "Never argue with an idiot they’ll drag you down to their level and beat you through experience",
            "Revenge is a Poison meant for others, which we end up swallowing ourselves.\n" + "Vengence is a Dark Light that blinds all who seek it.\n" +
                    "Don’t argue with Idiots.. They will bring you down to their level and beat you with experience.",
            "Why do we kill people who kill people to show people that killing people is wrong???",
            "Sorrow looks back,\n" + "Worry looks around,\n" + "Faith looks up!!",
            "If you argue with an idiot, after a while it is hard to tell who the real idiot is.",
            "Empty cans make the most noise.", "The wisest of people in this world are those that admit when they are wrong.",
            "Being quiet is fine, most people don’t listen anyways.",
            "When the power of love overcomes the love of power, the world will know peace.",
            "When you point a finger at someone you also have 3 fingers pointing at yourself, that means you must have a look at yourself at least 3 times before judging someone else.",
            "Never expect anything.\n" + "This way, you’ll never be disappointed.", "Don’t blame people for disappointing you, blame yourself for expecting too much from them.",
            "If the ocean was vodka and I was a duck I’d swim to the bottom and never come up.\n" + "But the ocean’s not vodka and I am not a duck so pass me a bottle and shut the f**k up!",
            "A drunk man’s words are a sober man’s thoughts.", "My boss didn’t know I drank, till one day I came to work sober.",
            "Of course I am gonna drive. I am too drunk to walk.",
            "Beer is now cheaper than gas, do drink, don’t drive!",
            "I went on a diet, stopped smoking dope, cut out the drinking and heavy eating, and in fourteen days I lost two weeks.",
            "Whiskey is risky but it makes the girls frisky.\n" + "Don’t be dumb and mix wine and rum.\n" + "Beer before liquor, never been sicker. Liquor before beer, you’re in the clear.\n" + "A good friend takes your drink away and says, “You’ve had enough.” But a TRUE friend gives you another drink and yells, “YOU BETTER CHUGG THIS CUZ WE AIN’T TRASHED YET!!”",
            "One tequila\n" + "two tequila\n" + "three takillya\n" + "floor", "I drink to make other people more interesting.",
            "I only drink on 2 occasions when I’m thirsty and when I’m not",
            "Everybody has to believe in something. I believe I’ll have another drink.",
            "I realized I was drinking too much,\n" + "So I decided to cut down,\n" + "I now only drink on days ending in Y",
            "I like to have a martini,\n" + "two at the very most,\n" + "after three I’m under the table,\n" + "after four I’m under my host.",
            "Parent says don’t drink\n" + "Friends says don’t drink\n" + "Cops says don’t drink\n" + "Are they saving it for themselves?",
            "A police officer said to a man “son your eyes looked red have you been drinking?”\n" + "Response from the man “gee officer your eyes looked glazed have you been eating doughnuts?”",
            "Ociffer you hass too listening to me, I swear to drunk I am not god!"};

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this weatherFragment
        View v = inflater.inflate(R.layout.fragment_phrase, container, false);
        tvPhrase = (TextView) v.findViewById(R.id.tvPhrase);
        fabShare = (FloatingActionButton) v.findViewById(R.id.fabShare);
        fabShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.putExtra(Intent.EXTRA_TEXT, tvPhrase.getText());
                sendIntent.setType("text/plain");
                startActivity(sendIntent);
            }
        });
        int rand = (int) (Math.random() * phrases.length);
        tvPhrase.setText(phrases[rand]);


        return v;
    }


}
