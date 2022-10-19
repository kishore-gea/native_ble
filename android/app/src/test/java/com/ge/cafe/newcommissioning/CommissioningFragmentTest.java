/*
 * Copyright GE Appliances, a Haier Company (Confidential). All rights reserved.
 */

package com.ge.cafe.newcommissioning;

import org.junit.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

public class CommissioningFragmentTest {

    @Test
    public void initialization() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException, InstantiationException {
        List<Class<?>> testClasses = new ArrayList<>();
        testClasses.add(CommissioningWelcomFragment.class);
        testClasses.add(CommissioningBasicFragment.class);

        for (Class<?> fragmentClass : testClasses) {
            Constructor<?> constructor = fragmentClass.getConstructor();

            // if the test doesn't crash here, we're good
            constructor.newInstance();
        }
    }
}
