package ru.aeroidea.sitnikovtesttask.Utils;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import com.google.android.material.appbar.AppBarLayout;

public class AppBarLayoutBehavior extends AppBarLayout.Behavior {

    private boolean enabled;

    public AppBarLayoutBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    @Override
    public boolean onStartNestedScroll(CoordinatorLayout parent, AppBarLayout child, View directTargetChild, View target, int nestedScrollAxes, int type) {
        return enabled && super.onStartNestedScroll(parent, child, directTargetChild, target, nestedScrollAxes, type);
    }

    public boolean isEnabled() {
        return enabled;
    }

}
