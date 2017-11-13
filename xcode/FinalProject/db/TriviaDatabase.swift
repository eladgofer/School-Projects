//
//  TriviaDatabase.swift
//  FinalProject
//
//  Created by elad gofer on 02/11/2017.
//  Copyright © 2017 elad gofer. All rights reserved.
//

import UIKit
typealias JsonObject = [String: Any]

class TriviaDatabase{

    static func getQuestions(listener: @escaping ([TriviaQA])->Void){
        let urlAddress = "https://opentdb.com/api.php?amount=40&category=9&type=boolean"
        
        guard let url:URL = URL(string: urlAddress) else {return}
        

        let session = URLSession(configuration: .default)
        

        session.dataTask(with: url) { (data, response, err) in
            guard let data = data else {
                
                print("error")
                return
            }
            let questions = parseJson(data: data)
            DispatchQueue.main.async {
                
                listener(questions)
            }
            
            }.resume()
    }


static func parseJson(data:Data)-> [TriviaQA]{
   var questions = [TriviaQA]()
    let dict = try! JSONSerialization.jsonObject(with: data, options: []) as! JsonObject
    
    
    let results = dict["results"] as! [JsonObject]
    
    for questionObject in results{
        let question = questionObject["question"] as! String
        let correctAnswer = questionObject["correct_answer"] as! String
        let category = questionObject["category"] as! String
        let q = TriviaQA(question: question, correctAnswer: correctAnswer, category: category)
        
        questions.append(q)
    }
    
    return questions
    }
}
