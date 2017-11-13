//
//  QuestionForDB.swift
//  FinalProject
//
//  Created by elad gofer on 06/11/2017.
//  Copyright © 2017 elad gofer. All rights reserved.
//

import UIKit
import FirebaseDatabase
class QuestionForDB{
    let question:String
    let correctAnswer:String
    let ownerId:String
    
    init(question:String, correctAnswer:String, ownerId:String) {
        self.question = question
        self.correctAnswer = correctAnswer

        self.ownerId = ownerId
    }
    init(json: [String: Any]) {
        self.ownerId = json["User"] as! String

        self.question = json["Question"] as! String
        self.correctAnswer = json["CorrectAnswer"] as! String
    }
    
    var description: String{
        return "\(ownerId)\n \(question) \n \(correctAnswer)"
    }
    
    var json:[String: Any]{
        return[
            "User": ownerId,
            "Question": question,
            "CorrectAnswer": correctAnswer
        ]
    }

}
