package com.example.expandablelayouttrial;

import android.os.Bundle;
import androidx.fragment.app.Fragment;


public interface NavigationHost {

    void navigateTo(String fragName, int layout, boolean addToBackStack, String tag, Bundle data);

    void saveStatus(Bundle data);
}
