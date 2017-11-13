//
//  SplitBillViewController.swift
//  FinalProject
//
//  Created by elad gofer on 22/10/2017.
//  Copyright © 2017 elad gofer. All rights reserved.
//

import UIKit

class SplitBillViewController: UIViewController, UITextFieldDelegate {
    
    @IBAction func infoTapped(_ sender: UIButton) {
        showAlert()
    }
    @IBOutlet var perPersonLabel: UILabel!
  
    @IBOutlet var billAmountTxt: UITextField!
    @IBOutlet var howManyLabel: UILabel!
    @IBAction func howManyStepper(_ sender: UIStepper) {
        howManyLabel.text = String(Int(sender.value))
        guard let b = billAmountTxt?.text else {return}
        
        if b.characters.count >= 1{
            let split = Double(b)! / Double(howManyLabel.text!)!
            perPersonLabel.text = String(split)
        }
    }
    override func viewDidLoad() {
        super.viewDidLoad()
        billAmountTxt.delegate = self
        howManyLabel.text = "1"
        billAmountTxt.addTarget(self, action: #selector(textFieldDidChange(_:)), for: .editingChanged)
        
        
    }
    
    func showAlert(){
        let alert = UIAlertController(title: "Split Bill", message: "Input how many people are paying, and how much was the bill and it will be divided equally among everyone", preferredStyle: .alert)
        
        let okAction = UIAlertAction(title: "Got It", style: .default) { (action) in
            print("it will...")
        }
        alert.addAction(okAction)
        present(alert, animated: true, completion: nil)
    }
    func textFieldDidChange(_ billAmountTxt: UITextField) {
        guard let bill = billAmountTxt.text else {return}
        if bill.characters.count >= 1{
        let split = Double(bill)! / Double(howManyLabel.text!)!
        perPersonLabel.text = String(describing: split)
        }
    }
    func textFieldShouldReturn(_ billAmountTxt: UITextField) -> Bool {
        billAmountTxt.resignFirstResponder()
        return true
    }
   
    

    /*
    // MARK: - Navigation

    // In a storyboard-based application, you will often want to do a little preparation before navigation
    override func prepare(for segue: UIStoryboardSegue, sender: Any?) {
        // Get the new view controller using segue.destinationViewController.
        // Pass the selected object to the new view controller.
    }
    */

}
