//
//  TriviaViewController.swift
//  FinalProject
//
//  Created by elad gofer on 02/11/2017.
//  Copyright © 2017 elad gofer. All rights reserved.
//

import UIKit
import AudioToolbox
import FirebaseAuth
import FirebaseDatabase
class TriviaViewController: UIViewController {
    var questions = [TriviaQA]()
    var firstNUm:Int!
    var i = 0
    @IBOutlet var nextOutlet: UIButton!
    @IBOutlet var trueOutlet: UIButton!
    @IBOutlet var falseOutlet: UIButton!
    @IBOutlet var answerForDB: UILabel!
    @IBAction func saveTapped(_ sender: UIButton) {
        
        
        let database = Database.database()
        let tableRef = database.reference(withPath: "Questions")
        
        let newRef = tableRef.childByAutoId()
        
        let auth = Auth.auth()
        guard let user = auth.currentUser else{ //---------------------------6
            print("no user")
            return
        }
        let newQuestion = QuestionForDB(question: self.questionLabel.text!, correctAnswer: answerForDB.text!, ownerId: user.uid)
        
        newRef.setValue(newQuestion.json) { (err, ref) in //----------------------------------8
            if let err = err{
                print(err)
            }
            else{
                
            }
        }
    }
    @IBOutlet var SaveOutlet: UIButton!
    @IBAction func nextTapped(_ sender: UIButton) {
        chalange.text = "What will it be...."
        i+=1
        let modifiedQuestion = questions[i].question.replacingOccurrences(of: "&#039;", with: "'")
        let finalQuestion = modifiedQuestion.replacingOccurrences(of: "&quot;", with: "''")
        self.questionLabel.text = "\(finalQuestion)"
        self.answerLabel.alpha = 0
        self.answerLabel.text = questions[i].correctAnswer
        self.answerForDB.text = questions[i].correctAnswer
        if i >= questions.count-1{
            i = 0
            
        }
        
    }
    @IBOutlet var chalange: UILabel!
    @IBOutlet var answerLabel: UILabel!
    @IBAction func FalseTapped(_ sender: UIButton) {
        checkAnswer("False")
    }
    @IBAction func trueTapped(_ sender: UIButton) {
        checkAnswer("True")
    }
    @IBOutlet var questionLabel: UILabel!
    
    @IBAction func infoTapped(_ sender: UIButton) {
        showAlert()
    }
    func showAlert(){
        let alert = UIAlertController(title: "PlayFor Drinks", message: "Guess the answer of the trivia question. get it righ and you get a reward, get it wrong and you get punished. \n Like a question and you want to keep it in your question Archive, just press on the save button when the question is visable on the screen and it will automatically be stored in the archive.", preferredStyle: .alert)
        
        let okAction = UIAlertAction(title: "Got It", style: .default) { (action) in
            
        }
        alert.addAction(okAction)
        present(alert, animated: true, completion: nil)
    }
    
    override func viewDidLoad() {
        super.viewDidLoad()
        getBorder(nextOutlet)
        getBorder(trueOutlet)
        getBorder(falseOutlet)
        getBorder(SaveOutlet)
        
        TriviaDatabase.getQuestions { (questions) in
            self.questions = questions
            self.view.reloadInputViews()
            self.firstNUm = Int.nextRandom(max: questions.count-1)
            let modifiedQuestion = questions[self.firstNUm].question.replacingOccurrences(of: "&#039;", with: "'")
            let finalQuestion = modifiedQuestion.replacingOccurrences(of: "&quot;", with: "''")
            self.questionLabel.text = "\(questions[0].category):\n \n \(finalQuestion)"
            self.answerLabel.alpha = 0
            self.answerLabel.text = questions[0].correctAnswer
            self.answerForDB.text = questions[0].correctAnswer
            
            
        }
        
        chalange.text = "What will it be...."
    }
    
    func checkAnswer(_ answer:String){
        
        if self.answerLabel.text == answer{
            self.answerLabel.text = "😄Good job!"
            chalange.text = "Reword: \(WinnerChalanges.getChalange())"
            
        }else {
            self.answerLabel.text = "🤦🏽‍♀️Better luck next time.."
            AudioServicesPlayAlertSound(kSystemSoundID_Vibrate)
            chalange.text = "Punishment: \(LooserChalanges.getLooserChalange())"
           
        }
        self.answerLabel.alpha = 1
    }
    
    func getBorder(_ btn:UIButton){
        btn.layer.cornerRadius = 5
        btn.layer.borderWidth = 1.5
        btn.layer.borderColor = UIColor.white.cgColor
    }

}
