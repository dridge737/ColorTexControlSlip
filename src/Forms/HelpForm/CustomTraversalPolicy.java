/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Forms.HelpForm;

import java.awt.Component;
import java.awt.Container;
import java.awt.FocusTraversalPolicy;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author Eldridge
 */
public class CustomTraversalPolicy extends FocusTraversalPolicy {

    private List<Component> order;

        public CustomTraversalPolicy(Component... order) {
            this.order = new ArrayList<>(Arrays.asList(order));
        }

        @Override
        public Component getComponentAfter(Container focusCycleRoot,
                        Component aComponent) {
            int idx = (order.indexOf(aComponent) + 1) % order.size();
            return order.get(idx);
        }

        @Override
        public Component getComponentBefore(Container focusCycleRoot,
                        Component aComponent) {
            int idx = order.indexOf(aComponent) - 1;
            if (idx < 0) {
                idx = order.size() - 1;
            }
            return order.get(idx);
        }

        @Override
        public Component getDefaultComponent(Container focusCycleRoot) {
            return order.get(0);
        }

        @Override
        public Component getLastComponent(Container focusCycleRoot) {
            return order.size() > 0 ? order.get(order.size()) : null;
        }

        @Override
        public Component getFirstComponent(Container focusCycleRoot) {
            return order.get(0);
        }
    
}
