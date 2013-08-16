//
//  BSFOGuardian.h
//  BarnehageSFO
//
//  Created by Jens Kristoffer Reitan Markussen on 16.08.13.
//  Copyright (c) 2013 Jens Kristoffer Reitan Markussen. All rights reserved.
//

#import <Foundation/Foundation.h>
#import "BSFOAddress.h"

@interface BSFOGuardian : NSObject

@property (nonatomic, strong) NSString* name;
@property (nonatomic, copy) NSString* phone;
@property (nonatomic, strong) BSFOAddress* address;

@end
