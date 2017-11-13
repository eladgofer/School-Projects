//
//  LooserChalanges.swift
//  FinalProject
//
//  Created by elad gofer on 05/11/2017.
//  Copyright © 2017 elad gofer. All rights reserved.
//

import UIKit

class LooserChalanges{
    
    static func getLooserChalange()->String{
        var chalange:String
        let chalangesArr = ["Down your drink","Take a shot","Taquila","Free pass 😅"]
        chalange = chalangesArr[Int.nextRandom(max: chalangesArr.count-1)]
        
        return chalange
    }


}
