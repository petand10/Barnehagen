//
//  BSFOAddress.h
//  BarnehageSFO
//
//  Created by Jens Kristoffer Reitan Markussen on 16.08.13.
//  Copyright (c) 2013 Jens Kristoffer Reitan Markussen. All rights reserved.
//

#import <Foundation/Foundation.h>

@interface BSFOAddress : NSObject

@property (nonatomic, strong) NSString* street;
@property (nonatomic, readwrite) NSUInteger postal;
@property (nonatomic, strong) NSString* postalArea;

@end
