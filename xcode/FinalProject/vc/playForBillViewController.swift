//
//  playForBillViewController.swift
//  FinalProject
//
//  Created by elad gofer on 23/10/2017.
//  Copyright © 2017 elad gofer. All rights reserved.
//

import UIKit

class playForBillViewController: UIViewController {
    
    @IBOutlet var stepperOutlet: UIStepper!
    var images = [UIImageView]()
    var howManyValue:Int!
    var emoji:String!
    var imageView:UIImageView!
    @IBAction func spinIt(_ sender: UIButton) {
        let r1 = Int.nextRandom(max: gameArray.count)
        slotPicker.selectRow(r1, inComponent: 0, animated: true)
        looserImage.alpha = 1

    }
    
    @IBAction func infoTapped(_ sender: UIButton) {
        showAlert()
    }
    func showAlert(){
        let alert = UIAlertController(title: "Play For The Bill", message: "Input how many people are playing, each person is to be asigned an icon, from the list that will apear. When you press the red button a random icon from the list will be picked, and that person has to pick up the tab", preferredStyle: .alert)
        
        let okAction = UIAlertAction(title: "Got It", style: .default) { (action) in
            print("it will...")
        }
        alert.addAction(okAction)
        present(alert, animated: true, completion: nil)
    }
    @IBOutlet var looserImage: UIImageView!
    var looser:UIImage!
    @IBOutlet var slotPicker: UIPickerView!

    @IBOutlet var howManyLabel: UILabel!
    var gameArray = [UIImage]()
    @IBAction func howManyStepper(_ sender: UIStepper) {
        howManyValue = Int(sender.value)
        howManyLabel.text = String(howManyValue)
        if gameArray.count > Int(stepperOutlet.value){
            players.append(gameArray[gameArray.count - 1])
            gameArray.remove(at: gameArray.count - 1)
        }else{
            gameArray.append(players[players.count - 1])
            players.remove(at: players.count - 1)
        }
        makeImageViews(Int(stepperOutlet.value))
        print(howManyValue)
        switch gameArray.count {
        case 0:
            removeImageView()
            break
        case 1:
            removeImageView()
            makeImageViews(1)
            break
        case 2:
            removeImageView()
            makeImageViews(2)
            break
        case 3:
            removeImageView()
            makeImageViews(3)
            break
        case 4:
            removeImageView()
            makeImageViews(4)
            break
        case 5:
            removeImageView()
            makeImageViews(5)
            break
        case 6:
            removeImageView()
            makeImageViews(6)
            break
        case 7:
            removeImageView()
            makeImageViews(7)
            break
        case 8:
            removeImageView()
            makeImageViews(8)
            break
        case 9:
            removeImageView()
            makeImageViews(9)
            break
        case 10:
            removeImageView()
            makeImageViews(10)
            break
        default:
            break
        }
    }
    func makeImageViews(_ x:Int){
        for i in 0..<x{
            imageView = UIImageView(image: gameArray[i])
            images.append(imageView)
            let dif = CGFloat(i * 40)
            let height = #imageLiteral(resourceName: "sumo").size.height / 3
            let width = #imageLiteral(resourceName: "sumo").size.width / 3
            imageView.frame = CGRect(x: dif, y: 230, width: #imageLiteral(resourceName: "sumo").size.width - width, height: #imageLiteral(resourceName: "sumo").size.height - height)
        
            view.addSubview(imageView)
        }
        self.slotPicker.reloadAllComponents()
    }
    func removeImageView(){
        for i in images{
            i.removeFromSuperview()
        }
    }
    override func viewDidLoad() {
        super.viewDidLoad()
        slotPicker.dataSource = self
        slotPicker.delegate = self
        
        
    }
    override func viewDidAppear(_ animated: Bool) {
        super.viewDidAppear(animated)
            
            slotPicker.selectRow(1 , inComponent: 0, animated: true)
            slotPicker.isUserInteractionEnabled = false
            looserImage.alpha = 0
    }
    
    lazy var players:[UIImage] = {
        var result = [#imageLiteral(resourceName: "gremlin"),#imageLiteral(resourceName: "dizzy_person"),#imageLiteral(resourceName: "ninja_turtle"),#imageLiteral(resourceName: "spiderman-old"),#imageLiteral(resourceName: "cow"),#imageLiteral(resourceName: "sumo"),#imageLiteral(resourceName: "amelie_poulain"),#imageLiteral(resourceName: "tardis"),#imageLiteral(resourceName: "budget"),#imageLiteral(resourceName: "poo")]
        return result
    }()


}

extension playForBillViewController : UIPickerViewDataSource{
    
    func numberOfComponents(in pickerView: UIPickerView) -> Int {
        return 1
    }
    
    func pickerView(_ pickerView: UIPickerView, numberOfRowsInComponent component: Int) -> Int {
        
        var howManyRows:Int
        if gameArray.count > 0{
            howManyRows = gameArray.count
        }else{
            howManyRows = 0
        }
        
        
        return howManyRows
    }
    
}

extension playForBillViewController : UIPickerViewDelegate{
    func pickerView(_ pickerView: UIPickerView, viewForRow row: Int, forComponent component: Int, reusing view: UIView?) -> UIView {
        let v:UIImageView
        if gameArray.count < 1{
            v = UIImageView(image: players[row % players.count])

        }else{
            v = UIImageView(image: gameArray[row % gameArray.count])
            looserImage.image = gameArray[slotPicker.selectedRow(inComponent: 0)]
            
            
        }
       
        return v
        
    }
    func pickerView(_ pickerView: UIPickerView, rowHeightForComponent component: Int) -> CGFloat {
        return #imageLiteral(resourceName: "cow").size.height * 2
    }

    
}
extension Int{
    static func nextRandom(max:Int) -> Int{
        let rand = Int(arc4random_uniform(UInt32(max)))
        
        return rand
    }
}


