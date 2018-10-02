//
//  BarcodeEventEmitter.m
//  ReactNativeCameraKit
//
//  Created by Gadi Gomez on 29/08/2018.
//  Copyright © 2018 Wix. All rights reserved.
//

#import "BarcodeEventEmitter.h"

// Notification/Event Names
NSString *const kBarcodeScanned = @"BarcodeEventEmitter/BarcodeScanned";

@implementation BarcodeEventEmitter

RCT_EXPORT_MODULE();

- (NSDictionary<NSString *, NSString *> *)constantsToExport {
    return @{ @"BARCODE_SCANNED": kBarcodeScanned,
              };
}

- (NSArray<NSString *> *)supportedEvents {
    return @[kBarcodeScanned,
             ];
}

- (void)startObserving {
    for (NSString *event in [self supportedEvents]) {
        [[NSNotificationCenter defaultCenter] addObserver:self
                                                 selector:@selector(handleNotification:)
                                                     name:event
                                                   object:nil];
    }
}

- (void)stopObserving {
    [[NSNotificationCenter defaultCenter] removeObserver:self];
}

# pragma mark Public

+ (BOOL)application:(UIApplication *)application didScanBarcode:(NSString *)barcodeString {
    [self postNotificationName:kBarcodeScanned withPayload:barcodeString];
    return YES;
}

# pragma mark Private

+ (void)postNotificationName:(NSString *)name withPayload:(NSObject *)object {
    NSDictionary<NSString *, id> *payload = @{@"payload": object};
    // gadi
    [[NSNotificationCenter defaultCenter] postNotificationName:name
                                                        object:self
                                                      userInfo:payload];
}

- (void)handleNotification:(NSNotification *)notification {
    [self sendEventWithName:notification.name body:notification.userInfo];
}

@end
