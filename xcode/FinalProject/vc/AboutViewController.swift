//
//  AboutViewController.swift
//  FinalProject
//
//  Created by elad gofer on 12/11/2017.
//  Copyright © 2017 elad gofer. All rights reserved.
//

import UIKit
import WebKit
class AboutViewController: UIViewController{

    @IBOutlet var webViewIcons: UIView!
    @IBAction func iconsTapped(_ sender: UIButton) {

        webViewIcons.isHidden = false
        webViewIcons.layer.borderColor = UIColor.brown.cgColor
        webViewIcons.layer.borderWidth = 8
        webViewIcons.layer.cornerRadius = 5
        
    }

    
    override func viewDidLoad() {
        super.viewDidLoad()
        

    }


}
