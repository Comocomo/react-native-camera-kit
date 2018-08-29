//
//  BarcodeEventEmitter.h
//  ReactNativeCameraKit
//
//  Created by Gadi Gomez on 29/08/2018.
//  Copyright © 2018 Wix. All rights reserved.
//

#if __has_include(<React/RCTBridge.h>)
#import <React/RCTEventEmitter.h>
#import <React/RCTBridge.h>
#else
#import "RCTEventEmitter.h"
#import "RCTBridge.h"
#endif


@interface BarcodeEventEmitter : RCTEventEmitter <RCTBridgeModule>

+ (BOOL)application:(UIApplication *)application didScanBarcode:(NSString *)barcodeString;

@end
