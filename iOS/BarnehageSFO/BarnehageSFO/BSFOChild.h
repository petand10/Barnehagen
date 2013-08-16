//
//  BSFOChild.h
//  BarnehageSFO
//
//  Created by Jens Kristoffer Reitan Markussen on 16.08.13.
//  Copyright (c) 2013 Jens Kristoffer Reitan Markussen. All rights reserved.
//

#import <Foundation/Foundation.h>

@interface BSFOChild : NSObject

@property (nonatomic, strong) NSString* name;
@property (nonatomic, strong) NSArray* guardians;

-(id)initWithName:(NSString*)name guardians:(NSArray*) guardians;


@end
