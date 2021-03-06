/*
 * The MIT License
 *
 * Copyright (c) 2013, Andreas Vogler
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package org.jenkinsci.plugins.pendingChanges;

import hudson.Extension;
import hudson.model.Job;
import hudson.scm.ChangeLogSet;
import hudson.views.ListViewColumn;
import hudson.views.ListViewColumnDescriptor;
import org.kohsuke.stapler.DataBoundConstructor;

public class PendingChangesColumn extends ListViewColumn
{
    @DataBoundConstructor
    public PendingChangesColumn() {
        super();
    }

    public boolean hasPendingChanges(Job job)
    {
        PendingChangesAction action = job.getAction(PendingChangesAction.class);
        if(action!=null) {
            Iterable it = action.getPendingChanges();
            return it!=null && it.iterator().hasNext();
        } else {
            return false;
        }
    }

    public Iterable<ChangeLogSet.Entry> getPendingChanges(Job job) {
        PendingChangesAction action = job.getAction(PendingChangesAction.class);
        if (action != null) {
            return action.getPendingChanges();
        } else {
            return null;
        }
    }

    @Extension
    public static class DescriptorImpl extends ListViewColumnDescriptor {

        @Override
        public boolean shownByDefault() {
            return false;
        }

        @Override
        public String getDisplayName() {
            return Messages.displayName();
        }

    }
}
