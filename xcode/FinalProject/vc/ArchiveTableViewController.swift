//
//  ArchiveTableViewController.swift
//  FinalProject
//
//  Created by elad gofer on 06/11/2017.
//  Copyright © 2017 elad gofer. All rights reserved.
//

import UIKit
import FirebaseAuth
import FirebaseDatabase
class ArchiveTableViewController: UITableViewController {
    var questions = [QuestionForDB]()
    override func viewDidLoad() {
        super.viewDidLoad()
        let questionsRef = Database.database().reference(withPath: "Questions")
        
        questionsRef.observe(.childAdded, with:  { (snapShot) in
            let json = snapShot.value as! [String: Any]
            
            let question = QuestionForDB(json: json)
            
            self.questions.append(question)
            
            let path = IndexPath(row: self.questions.count - 1, section: 0)
            self.tableView.insertRows(at: [path], with: .automatic)
        })
        // Uncomment the following line to preserve selection between presentations
        // self.clearsSelectionOnViewWillAppear = false

        // Uncomment the following line to display an Edit button in the navigation bar for this view controller.
        // self.navigationItem.rightBarButtonItem = self.editButtonItem
    }

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }

    // MARK: - Table view data source

    override func numberOfSections(in tableView: UITableView) -> Int {
        // #warning Incomplete implementation, return the number of sections
        return 1
    }

    override func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        // #warning Incomplete implementation, return the number of rows
        return questions.count
    }

    
    override func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        let cell = tableView.dequeueReusableCell(withIdentifier: "questionArchive", for: indexPath) as! QuestionTableViewCell

        let q = questions[indexPath.row]
        
        cell.questionLabel.text = "Question: \(q.question)"
        cell.answerLabel.text = "Answer: \(q.correctAnswer)"
        

        return cell
    }
 

    /*
    // Override to support conditional editing of the table view.
    override func tableView(_ tableView: UITableView, canEditRowAt indexPath: IndexPath) -> Bool {
        // Return false if you do not want the specified item to be editable.
        return true
    }
    */

    /*
    // Override to support editing the table view.
    override func tableView(_ tableView: UITableView, commit editingStyle: UITableViewCellEditingStyle, forRowAt indexPath: IndexPath) {
        if editingStyle == .delete {
            // Delete the row from the data source
            tableView.deleteRows(at: [indexPath], with: .fade)
        } else if editingStyle == .insert {
            // Create a new instance of the appropriate class, insert it into the array, and add a new row to the table view
        }    
    }
    */

    /*
    // Override to support rearranging the table view.
    override func tableView(_ tableView: UITableView, moveRowAt fromIndexPath: IndexPath, to: IndexPath) {

    }
    */

    /*
    // Override to support conditional rearranging of the table view.
    override func tableView(_ tableView: UITableView, canMoveRowAt indexPath: IndexPath) -> Bool {
        // Return false if you do not want the item to be re-orderable.
        return true
    }
    */

    /*
    // MARK: - Navigation

    // In a storyboard-based application, you will often want to do a little preparation before navigation
    override func prepare(for segue: UIStoryboardSegue, sender: Any?) {
        // Get the new view controller using segue.destinationViewController.
        // Pass the selected object to the new view controller.
    }
    */

}
