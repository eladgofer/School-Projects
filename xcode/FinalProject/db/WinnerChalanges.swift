//
//  WinnerChalanges.swift
//  FinalProject
//
//  Created by elad gofer on 05/11/2017.
//  Copyright © 2017 elad gofer. All rights reserved.
//

import UIKit

class WinnerChalanges {

    static func getChalange()->String{
        var chalange:String
        let chalangesArr = ["Choose one person from the table that has to take a shot of TAQUILA","You get a free round from your friends","You are the master! choose a looser that needs to take a shot","Vodke is nice, preferably twice! choose two friends and make them take a shot of vodka","No chalanges this time your friends get a free pass","Everyone in the table needs to down their drink!"]

        chalange = chalangesArr[Int.nextRandom(max: chalangesArr.count-1)]
        
            return chalange
    }
}
