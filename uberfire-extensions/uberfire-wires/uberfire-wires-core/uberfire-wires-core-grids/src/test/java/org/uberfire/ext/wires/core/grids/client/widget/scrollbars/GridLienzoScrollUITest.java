/*
 * Copyright 2017 Red Hat, Inc. and/or its affiliates.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.uberfire.ext.wires.core.grids.client.widget.scrollbars;

import com.google.gwt.dom.client.Style;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwtmockito.GwtMockitoTestRunner;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;

import static org.junit.Assert.*;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

@RunWith(GwtMockitoTestRunner.class)
public class GridLienzoScrollUITest {

    @Mock
    private AbsolutePanel panel;

    @Mock
    private Style style;

    @Mock
    private GridLienzoScrollHandler gridLienzoScrollHandler;

    private GridLienzoScrollUI gridLienzoScrollUI;

    @Before
    public void setUp() {
        this.gridLienzoScrollUI = spy(new GridLienzoScrollUI(gridLienzoScrollHandler));

        doReturn(style).when(gridLienzoScrollUI).style(any());
    }

    @Test
    public void testSetup() {

        gridLienzoScrollUI.setup();

        verify(gridLienzoScrollUI).applyMainPanelStyle();
        verify(gridLienzoScrollUI).applyMakeInternalScrollPanel();
        verify(gridLienzoScrollUI).applyDomElementContainerStyle();
    }

    @Test
    public void testApplyMainPanelStyle() {

        gridLienzoScrollUI.applyMainPanelStyle();

        verify(style).setPosition(Style.Position.RELATIVE);
        verify(style).setOverflow(Style.Overflow.SCROLL);
        verify(style).setZIndex(1);
    }

    @Test
    public void testApplyMakeInternalScrollPanel() {

        gridLienzoScrollUI.applyMakeInternalScrollPanel();

        verify(style).setPosition(Style.Position.ABSOLUTE);
    }

    @Test
    public void testApplyDomElementContainerStyle() {

        doReturn(style).when(gridLienzoScrollUI).style(any());

        gridLienzoScrollUI.applyDomElementContainerStyle();

        verify(style).setPosition(Style.Position.FIXED);
    }

    @Test
    public void testStyle() {

        final Widget widget = mock(Widget.class);
        final Element element = mock(Element.class);
        final Style expectedStyle = mock(Style.class);

        doReturn(expectedStyle).when(element).getStyle();
        doReturn(element).when(widget).getElement();
        doCallRealMethod().when(gridLienzoScrollUI).style(any());

        final Style actualStyle = gridLienzoScrollUI.style(widget);

        assertEquals(expectedStyle,
                     actualStyle);
    }
}
