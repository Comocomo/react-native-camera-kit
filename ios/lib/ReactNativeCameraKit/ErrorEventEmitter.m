//
//  ErrorEventEmitter.m
//  ReactNativeCameraKit
//
//  Created by Tomer Kobrinsky on 08/07/2020.
//  Copyright © 2020 Wix. All rights reserved.
//


#import "ErrorEventEmitter.h"

// Notification/Event Names
NSString *const error = @"ErrorEventEmitter/error";

@implementation ErrorEventEmitter

RCT_EXPORT_MODULE();

- (NSDictionary<NSString *, NSString *> *)constantsToExport {
    return @{ @"Error": error,
              };
}

- (NSArray<NSString *> *)supportedEvents {
    return @[error,
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

+ (BOOL)application:(UIApplication *)application errorEvent:(NSString *)errorString {
    [self postNotificationName:error withPayload:errorString];
    return YES;
}

# pragma mark Private

+ (void)postNotificationName:(NSString *)name withPayload:(NSObject *)object {
    NSDictionary<NSString *, id> *payload = @{@"payload": object};
    [[NSNotificationCenter defaultCenter] postNotificationName:name
                                                        object:self
                                                      userInfo:payload];
}

- (void)handleNotification:(NSNotification *)notification {
    [self sendEventWithName:notification.name body:notification.userInfo];
}

@end
