//
//  ErrorEventEmitter.h
//  ReactNativeCameraKit
//
//  Created by Tomer Kobrinsky on 08/07/2020.
//  Copyright © 2020 Wix. All rights reserved.
//

#if __has_include(<React/RCTBridge.h>)
#import <React/RCTEventEmitter.h>
#import <React/RCTBridge.h>
#else
#import "RCTEventEmitter.h"
#import "RCTBridge.h"
#endif


@interface ErrorEventEmitter : RCTEventEmitter <RCTBridgeModule>

+ (BOOL)application:(UIApplication *)application errorEvent:(NSString *)errorString;

@end
