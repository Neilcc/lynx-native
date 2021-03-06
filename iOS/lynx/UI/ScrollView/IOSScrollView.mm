// Copyright 2017 The Lynx Authors. All rights reserved.

#import "IOSScrollView.h"

@implementation IOSScrollView

- (void)layoutSubviews {
    [super layoutSubviews];
}

- (id)initWithUI:(LynxUI *)ui {
    self = [super init];
    if(self != nil) {
        _ui = ui;
        self.contentOffset = CGPointZero;
        self.scrollEnabled = YES;
        self.showsVerticalScrollIndicator = FALSE;
        self.showsHorizontalScrollIndicator = FALSE;
        // clipsToBound and maskToBounds are are functionally equivalent
        // http://stackoverflow.com/questions/1177775/how-is-the-relation-between-uiviews-clipstobounds-and-calayers-maskstobounds
        self.clipsToBounds = YES;
        self.delegate = self;
    }
    return self;
}

// 是否支持滑动至顶部
- (BOOL)scrollViewShouldScrollToTop:(UIScrollView *)scrollView
{
    return YES;
}

// 滑动到顶部时调用该方法
- (void)scrollViewDidScrollToTop:(UIScrollView *)scrollView
{
    
}

// scrollView 已经滑动
- (void)scrollViewDidScroll:(UIScrollView *)scrollView
{
    
}

@end
