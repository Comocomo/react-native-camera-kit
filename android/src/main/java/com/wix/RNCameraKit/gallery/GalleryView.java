package com.wix.RNCameraKit.gallery;

import android.content.Context;
import android.graphics.Rect;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.util.AttributeSet;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class GalleryView extends RecyclerView {

    private class GridLayoutViewManagerWrapper extends GridLayoutManager {

        GridLayoutViewManagerWrapper(Context context, int spanCount) {
            super(context, spanCount);
        }

        @Override
        public void onLayoutChildren(RecyclerView.Recycler recycler, State state) {
            try {
                super.onLayoutChildren(recycler, state);
            } catch (IndexOutOfBoundsException e) {
                Log.e("WIX", "IOOBE in RecyclerView");
            }
        }

        @Override
        public RecyclerView.LayoutParams generateDefaultLayoutParams() {
            return spanLayoutSize(super.generateDefaultLayoutParams());
        }

        @Override
        public RecyclerView.LayoutParams generateLayoutParams(Context c, AttributeSet attrs) {
            return spanLayoutSize(super.generateLayoutParams(c, attrs));
        }

        @Override
        public RecyclerView.LayoutParams generateLayoutParams(ViewGroup.LayoutParams lp) {
            return spanLayoutSize(super.generateLayoutParams(lp));
        }

        private RecyclerView.LayoutParams spanLayoutSize(RecyclerView.LayoutParams layoutParams){
            if(getOrientation() == HORIZONTAL){
                layoutParams.width = (int) Math.round(getHorizontalSpace() / Math.ceil(getItemCount() / getSpanCount()));
            }
            else if(getOrientation() == VERTICAL){
                layoutParams.height = (int) Math.round(getVerticalSpace() / Math.ceil(getItemCount() / getSpanCount()));
            }
            return layoutParams;
        }

        private int getHorizontalSpace() {
            return getWidth() - getPaddingRight() - getPaddingLeft();
        }

        private int getVerticalSpace() {
            return getHeight() - getPaddingBottom() - getPaddingTop();
        }
    }

    private int itemSpacing;
    private int lineSpacing;
    private boolean isHorizontal;
    private int columnCount;

    public GalleryView(Context context) {
        super(context);
        setHasFixedSize(true);
        getRecycledViewPool().setMaxRecycledViews(0, 20);
    }

    private void updateDecorator() {
        addItemDecoration(new ItemDecoration() {
            @Override
            public void getItemOffsets(Rect outRect, View view, RecyclerView parent, State state) {
                outRect.top = lineSpacing;
                outRect.left = itemSpacing;
                outRect.right = itemSpacing;
                outRect.bottom = lineSpacing;
            }
        });
    }

    public void setItemSpacing(int itemSpacing) {
        this.itemSpacing = itemSpacing;
        updateDecorator();
    }

    public void setIsHorizontal(boolean isHorizontal) {
        this.isHorizontal = isHorizontal;

    }

    public void setLineSpacing(int lineSpacing) {
        this.lineSpacing = lineSpacing;
        updateDecorator();
    }

    public void setColumnCount(int columnCount) {
        if (getLayoutManager() == null || ((GridLayoutViewManagerWrapper) getLayoutManager()).getSpanCount() != columnCount) {
            GridLayoutManager layoutManager = new GridLayoutViewManagerWrapper(getContext(), columnCount);
            layoutManager.setOrientation(GridLayoutManager.VERTICAL);
            setLayoutManager(layoutManager);
        }
    }

}
