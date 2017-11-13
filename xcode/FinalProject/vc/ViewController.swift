//
//  ViewController.swift
//  FinalProject
//
//  Created by elad gofer on 26/09/2017.
//  Copyright © 2017 elad gofer. All rights reserved.
//

import UIKit
import FirebaseAuth
import Firebase
class ViewController: UIViewController {

   
    
    
    
    @IBAction func aboutTapped(_ sender: UIButton) {
        
    }

    
    override func viewDidLoad() {
        super.viewDidLoad()
        let auth = Auth.auth()
        auth.signInAnonymously { (user, error) in
            
            // check if we have error or not:
            if let error = error{
                print(error)
                return
            }
            // check if we have a user or not
            if let user = user {
                print("We have a user!\(user)")
            }
        }
    }
}


