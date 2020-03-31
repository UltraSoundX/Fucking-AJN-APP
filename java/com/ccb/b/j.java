package com.ccb.b;

import android.text.Editable;
import android.widget.EditText;
import com.ccb.b.a.g;

class j implements g {
    final /* synthetic */ EditText a;
    final /* synthetic */ a b;

    j(a aVar, EditText editText) {
        this.b = aVar;
        this.a = editText;
    }

    public void a(String str) {
        if (a.l != null) {
            int selectionEnd = a.l.getSelectionEnd();
            if (-1 == selectionEnd) {
                return;
            }
            if (this.b.k <= 0 || selectionEnd != this.b.k) {
                Editable text = a.l.getText();
                if (this.b.i && this.b.h != null) {
                    if (this.b.j) {
                        text.append(8226);
                    } else {
                        text.append(str);
                    }
                    this.b.h.a(str);
                } else if (this.b.j && !this.b.i) {
                    text.append(8226);
                    this.b.z = this.b.z + str;
                } else if (!this.b.i) {
                    text.insert(selectionEnd, str);
                }
                a.l.setSelection(a.l.getSelectionEnd());
            }
        }
    }

    public void b(String str) {
        if (str.equals("delete")) {
            int selectionEnd = a.l.getSelectionEnd();
            if (-1 != selectionEnd && selectionEnd != 0) {
                Editable text = a.l.getText();
                if (text.length() > 0) {
                    text.delete(selectionEnd - 1, selectionEnd);
                }
                a.l.setSelection(selectionEnd - 1);
                if (this.b.i && this.b.h != null) {
                    this.b.h.a(1);
                } else if (this.b.j && !this.b.i && this.b.z != null && this.b.z != "") {
                    this.b.z = this.b.z.substring(0, this.b.z.length() - 1);
                }
            }
        } else if (str.equals("cancel")) {
            if (this.b.i && this.b.h != null) {
                this.b.B = "";
                this.b.E = "";
                this.a.setText("");
            } else if (this.b.j && !this.b.i) {
                this.a.setText("");
                this.b.z = "";
            }
            this.b.h(a.l);
        } else if (str.equals("shiftSys")) {
            this.b.f(a.l);
            this.a.setText("");
        } else if (str.equals("finish")) {
            this.b.h(a.l);
        }
    }

    public void a() {
    }
}
