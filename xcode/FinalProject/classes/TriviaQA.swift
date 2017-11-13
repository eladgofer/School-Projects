//
//  TriviaQA.swift
//  FinalProject
//
//  Created by elad gofer on 26/10/2017.
//  Copyright © 2017 elad gofer. All rights reserved.
//

import UIKit

class TriviaQA: CustomStringConvertible{
    let question:String
    let correctAnswer:String
    let category:String
        
    init(question:String, correctAnswer:String, category:String) {
        self.question = question
        self.correctAnswer = correctAnswer
        self.category = category
        
    }

    
    var description: String{
        return "\(question) \n \(correctAnswer) \n \(category)"
    }
    
}
