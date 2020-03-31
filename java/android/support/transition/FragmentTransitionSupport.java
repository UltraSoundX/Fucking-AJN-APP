package android.support.transition;

import android.graphics.Rect;
import android.support.transition.Transition.b;
import android.support.transition.Transition.c;
import android.support.v4.app.FragmentTransitionImpl;
import android.view.View;
import android.view.ViewGroup;
import java.util.ArrayList;
import java.util.List;

public class FragmentTransitionSupport extends FragmentTransitionImpl {
    public boolean canHandle(Object obj) {
        return obj instanceof Transition;
    }

    public Object cloneTransition(Object obj) {
        if (obj != null) {
            return ((Transition) obj).clone();
        }
        return null;
    }

    public Object wrapTransitionInSet(Object obj) {
        if (obj == null) {
            return null;
        }
        TransitionSet transitionSet = new TransitionSet();
        transitionSet.a((Transition) obj);
        return transitionSet;
    }

    public void setSharedElementTargets(Object obj, View view, ArrayList<View> arrayList) {
        TransitionSet transitionSet = (TransitionSet) obj;
        List g = transitionSet.g();
        g.clear();
        int size = arrayList.size();
        for (int i = 0; i < size; i++) {
            bfsAddViewChildren(g, (View) arrayList.get(i));
        }
        g.add(view);
        arrayList.add(view);
        addTargets(transitionSet, arrayList);
    }

    public void setEpicenter(Object obj, View view) {
        if (view != null) {
            Transition transition = (Transition) obj;
            final Rect rect = new Rect();
            getBoundsOnScreen(view, rect);
            transition.a((b) new b() {
                public Rect a(Transition transition) {
                    return rect;
                }
            });
        }
    }

    public void addTargets(Object obj, ArrayList<View> arrayList) {
        Transition transition = (Transition) obj;
        if (transition != null) {
            if (transition instanceof TransitionSet) {
                TransitionSet transitionSet = (TransitionSet) transition;
                int r = transitionSet.r();
                for (int i = 0; i < r; i++) {
                    addTargets(transitionSet.b(i), arrayList);
                }
            } else if (!a(transition) && isNullOrEmpty(transition.g())) {
                int size = arrayList.size();
                for (int i2 = 0; i2 < size; i2++) {
                    transition.c((View) arrayList.get(i2));
                }
            }
        }
    }

    private static boolean a(Transition transition) {
        return !isNullOrEmpty(transition.f()) || !isNullOrEmpty(transition.h()) || !isNullOrEmpty(transition.i());
    }

    public Object mergeTransitionsTogether(Object obj, Object obj2, Object obj3) {
        TransitionSet transitionSet = new TransitionSet();
        if (obj != null) {
            transitionSet.a((Transition) obj);
        }
        if (obj2 != null) {
            transitionSet.a((Transition) obj2);
        }
        if (obj3 != null) {
            transitionSet.a((Transition) obj3);
        }
        return transitionSet;
    }

    public void scheduleHideFragmentView(Object obj, final View view, final ArrayList<View> arrayList) {
        ((Transition) obj).a((c) new c() {
            public void d(Transition transition) {
            }

            public void a(Transition transition) {
                transition.b((c) this);
                view.setVisibility(8);
                int size = arrayList.size();
                for (int i = 0; i < size; i++) {
                    ((View) arrayList.get(i)).setVisibility(0);
                }
            }

            public void b(Transition transition) {
            }

            public void c(Transition transition) {
            }
        });
    }

    public Object mergeTransitionsInSequence(Object obj, Object obj2, Object obj3) {
        Transition transition = null;
        Transition transition2 = (Transition) obj;
        Transition transition3 = (Transition) obj2;
        Transition transition4 = (Transition) obj3;
        if (transition2 != null && transition3 != null) {
            transition = new TransitionSet().a(transition2).a(transition3).a(1);
        } else if (transition2 != null) {
            transition = transition2;
        } else if (transition3 != null) {
            transition = transition3;
        }
        if (transition4 == null) {
            return transition;
        }
        TransitionSet transitionSet = new TransitionSet();
        if (transition != null) {
            transitionSet.a(transition);
        }
        transitionSet.a(transition4);
        return transitionSet;
    }

    public void beginDelayedTransition(ViewGroup viewGroup, Object obj) {
        s.a(viewGroup, (Transition) obj);
    }

    public void scheduleRemoveTargets(Object obj, Object obj2, ArrayList<View> arrayList, Object obj3, ArrayList<View> arrayList2, Object obj4, ArrayList<View> arrayList3) {
        final Object obj5 = obj2;
        final ArrayList<View> arrayList4 = arrayList;
        final Object obj6 = obj3;
        final ArrayList<View> arrayList5 = arrayList2;
        final Object obj7 = obj4;
        final ArrayList<View> arrayList6 = arrayList3;
        ((Transition) obj).a((c) new c() {
            public void d(Transition transition) {
                if (obj5 != null) {
                    FragmentTransitionSupport.this.replaceTargets(obj5, arrayList4, null);
                }
                if (obj6 != null) {
                    FragmentTransitionSupport.this.replaceTargets(obj6, arrayList5, null);
                }
                if (obj7 != null) {
                    FragmentTransitionSupport.this.replaceTargets(obj7, arrayList6, null);
                }
            }

            public void a(Transition transition) {
            }

            public void b(Transition transition) {
            }

            public void c(Transition transition) {
            }
        });
    }

    public void swapSharedElementTargets(Object obj, ArrayList<View> arrayList, ArrayList<View> arrayList2) {
        TransitionSet transitionSet = (TransitionSet) obj;
        if (transitionSet != null) {
            transitionSet.g().clear();
            transitionSet.g().addAll(arrayList2);
            replaceTargets(transitionSet, arrayList, arrayList2);
        }
    }

    public void replaceTargets(Object obj, ArrayList<View> arrayList, ArrayList<View> arrayList2) {
        Transition transition = (Transition) obj;
        if (transition instanceof TransitionSet) {
            TransitionSet transitionSet = (TransitionSet) transition;
            int r = transitionSet.r();
            for (int i = 0; i < r; i++) {
                replaceTargets(transitionSet.b(i), arrayList, arrayList2);
            }
        } else if (!a(transition)) {
            List g = transition.g();
            if (g.size() == arrayList.size() && g.containsAll(arrayList)) {
                int size = arrayList2 == null ? 0 : arrayList2.size();
                for (int i2 = 0; i2 < size; i2++) {
                    transition.c((View) arrayList2.get(i2));
                }
                for (int size2 = arrayList.size() - 1; size2 >= 0; size2--) {
                    transition.d((View) arrayList.get(size2));
                }
            }
        }
    }

    public void addTarget(Object obj, View view) {
        if (obj != null) {
            ((Transition) obj).c(view);
        }
    }

    public void removeTarget(Object obj, View view) {
        if (obj != null) {
            ((Transition) obj).d(view);
        }
    }

    public void setEpicenter(Object obj, final Rect rect) {
        if (obj != null) {
            ((Transition) obj).a((b) new b() {
                public Rect a(Transition transition) {
                    if (rect == null || rect.isEmpty()) {
                        return null;
                    }
                    return rect;
                }
            });
        }
    }
}
