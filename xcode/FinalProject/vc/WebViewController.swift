//
//  WebViewController.swift
//  FinalProject
//
//  Created by elad gofer on 12/11/2017.
//  Copyright © 2017 elad gofer. All rights reserved.
//

import UIKit
import WebKit
class WebViewController: UIViewController, WKUIDelegate{
var webView: WKWebView!
    
    override func loadView() {
        let webConfiguration = WKWebViewConfiguration()
        webView = WKWebView(frame: .zero, configuration: webConfiguration)
        webView.uiDelegate = self
        view = webView
    }
    
    override func viewDidLoad() {
        super.viewDidLoad()
        self.view.layer.cornerRadius = 5
        let myURL = URL(string: "https://www.icons8.com")
        let myRequest = URLRequest(url: myURL!)
        webView.load(myRequest)
    }


    


}
