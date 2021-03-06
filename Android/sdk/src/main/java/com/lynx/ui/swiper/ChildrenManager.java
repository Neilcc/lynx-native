// Copyright 2017 The Lynx Authors. All rights reserved.
package com.lynx.ui.swiper;

import android.content.Context;
import android.view.ViewGroup;

import com.lynx.core.impl.RenderObjectImpl;
import com.lynx.ui.LynxUI;
import com.lynx.ui.LynxUIFactory;

import java.util.ArrayList;
import java.util.List;

public class ChildrenManager extends BaseChildrenManager {

    private Context mContext;
    protected List<RenderObjectImpl> mRenderObjectImplList;

    public ChildrenManager(Context context) {
        mContext = context;
        mRenderObjectImplList = new ArrayList<>();
    }

    @Override
    public void appendChild(int index, RenderObjectImpl child) {
        mRenderObjectImplList.add(index, child);
    }

    @Override
    public RenderObjectImpl removeChild(int index) {
        return mRenderObjectImplList.remove(index);
    }

    @Override
    public void removeChild(RenderObjectImpl removed) {
        int index = mRenderObjectImplList.indexOf(removed);
        if (index >= 0) {
            removeChild(index);
        }
    }

    @Override
    public LynxUI makeUI(int position) {
        return buildUI(position);
    }

    @Override
    public int getChildCount() {
        return mRenderObjectImplList.size();
    }

    @Override
    public int indexOf(RenderObjectImpl impl) {
        return mRenderObjectImplList.indexOf(impl);
    }

    protected LynxUI buildUI(int position) {

        RenderObjectImpl impl = mRenderObjectImplList.get(position);

        buildChildrenUI(null, impl);

        return impl.getUI();
    }

    private void buildChildrenUI(RenderObjectImpl parent, RenderObjectImpl impl) {

        if (impl.getUI() != null) {
            return;
        }

        int type = impl.getRenderObjectType();

        LynxUI ui = mRecyclerBin.getRecycledUI(type);

        if (ui != null) {
            ui.bindData(impl);
        } else {
            ui = LynxUIFactory.create(mContext, impl);
        }

        for (int i = 0; i < impl.getChildCount(); i++) {
            buildChildrenUI(impl, impl.getChildAt(i));
        }

        if (parent != null && parent.getUI() != null) {
            ((ViewGroup)parent.getUI().getView()).addView(ui.getView());
        }
    }
}
