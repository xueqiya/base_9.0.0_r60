/*
 * Copyright (C) 2017 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.android.systemui.doze;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.withSettings;

import com.android.internal.hardware.AmbientDisplayConfiguration;
import com.android.systemui.statusbar.phone.DozeParameters;

import org.mockito.Answers;
import org.mockito.MockSettings;

public class DozeConfigurationUtil {
    public static DozeParameters createMockParameters() {
        boolean[] doneHolder = new boolean[1];
        DozeParameters params = mock(DozeParameters.class, noDefaultAnswer(doneHolder));

        when(params.getPulseOnSigMotion()).thenReturn(false);
        when(params.getPickupVibrationThreshold()).thenReturn(0);
        when(params.getProxCheckBeforePulse()).thenReturn(true);
        when(params.getPickupSubtypePerformsProxCheck(anyInt())).thenReturn(true);

        doneHolder[0] = true;
        return params;
    }

    public static AmbientDisplayConfiguration createMockConfig() {
        boolean[] doneHolder = new boolean[1];
        AmbientDisplayConfiguration config = mock(AmbientDisplayConfiguration.class,
                noDefaultAnswer(doneHolder));
        when(config.pulseOnDoubleTapEnabled(anyInt())).thenReturn(false);
        when(config.pulseOnPickupEnabled(anyInt())).thenReturn(false);
        when(config.pulseOnNotificationEnabled(anyInt())).thenReturn(true);

        when(config.doubleTapSensorType()).thenReturn(null);
        when(config.pulseOnPickupAvailable()).thenReturn(false);

        doneHolder[0] = true;
        return config;
    }

    private static MockSettings noDefaultAnswer(boolean[] setupDoneHolder) {
        return withSettings().defaultAnswer((i) -> {
            if (setupDoneHolder[0]) {
                throw new IllegalArgumentException("not defined");
            } else {
                return Answers.RETURNS_DEFAULTS.answer(i);
            }
        });
    }

}
