// Copyright 2017 The Lynx Authors. All rights reserved.

#import "LynxUIEventAction.h"

@implementation LynxUIEventAction

- (id) initWithTarget:(LynxRenderObjectImpl *)target andEvent:(NSString *)event andValue:(NSMutableArray *)array {
    self = [super initWithType:DO_EVENT_ACTION andTarget:target];
    if (self) {
        _event = event;
        _array = array;
    }
    return self;
}

- (void)doAction {
    [self.renderObjectImpl dispatchEvent:_event withParam:_array];
}

@end
