//
//  TipCalcViewController.swift
//  FinalProject
//
//  Created by elad gofer on 23/10/2017.
//  Copyright © 2017 elad gofer. All rights reserved.
//

import UIKit

class TipCalcViewController: UIViewController, UITextFieldDelegate {
    var percentValue:Double!
    var howManyValue:Double!
    var perPerson:Double!
    var theTip:Double!
    @IBOutlet var tipPerPersonLabel: UILabel!
    @IBOutlet var totalTipLabel: UILabel!
    @IBOutlet var billAmountTxt: UITextField!
    @IBOutlet var percentLabel: UILabel!
    @IBAction func percentStepper(_ sender: UIStepper) {
        percentValue = sender.value
        percentLabel.text = "\(Int(percentValue))%"
        doTheMath()
        
    }
    @IBOutlet var howManyLabel: UILabel!
    @IBAction func howManyStepper(_ sender: UIStepper) {
        howManyValue = sender.value
        howManyLabel.text = "\(Int(howManyValue))"
        doTheMath()
    }
    @IBAction func infoTapped(_ sender: Any) {
        showAlert()
    }
    func showAlert(){
        let alert = UIAlertController(title: "Calculate The Tip", message: "Input how many people are paying, how much you wan't to tip in percent, and The bill amount. And find out how much tip you need leave ", preferredStyle: .alert)
        
        let okAction = UIAlertAction(title: "Got It", style: .default) { (action) in
            print("it will...")
        }
        alert.addAction(okAction)
        present(alert, animated: true, completion: nil)
    }
    override func viewDidLoad() {
        billAmountTxt.delegate = self
        super.viewDidLoad()
        billAmountTxt.addTarget(self, action: #selector(textFieldDidChange(_:)), for: .editingChanged)
        howManyValue = 1
        percentValue = 5
        
    }

    func textFieldDidChange(_ billAmountTxt: UITextField){
        doTheMath()
    }
    
    func textFieldShouldReturn(_ billAmountTxt: UITextField) -> Bool {
        billAmountTxt.resignFirstResponder()
        return true
    }
    
    func doTheMath(){
        guard let bill = billAmountTxt.text else {return}
        if bill.characters.count >= 1{
            theTip = percentValue / 100 * Double(bill)!
            totalTipLabel.text = String(format: "%.2f", theTip)
            perPerson = theTip / howManyValue
            tipPerPersonLabel.text = String(format: "%.2f", perPerson)
        }
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
