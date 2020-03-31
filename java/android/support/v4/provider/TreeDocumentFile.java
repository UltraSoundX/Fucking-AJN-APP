package android.support.v4.provider;

import android.content.Context;
import android.net.Uri;
import android.provider.DocumentsContract;

class TreeDocumentFile extends DocumentFile {
    private Context mContext;
    private Uri mUri;

    TreeDocumentFile(DocumentFile documentFile, Context context, Uri uri) {
        super(documentFile);
        this.mContext = context;
        this.mUri = uri;
    }

    public DocumentFile createFile(String str, String str2) {
        Uri createFile = createFile(this.mContext, this.mUri, str, str2);
        if (createFile != null) {
            return new TreeDocumentFile(this, this.mContext, createFile);
        }
        return null;
    }

    private static Uri createFile(Context context, Uri uri, String str, String str2) {
        try {
            return DocumentsContract.createDocument(context.getContentResolver(), uri, str, str2);
        } catch (Exception e) {
            return null;
        }
    }

    public DocumentFile createDirectory(String str) {
        Uri createFile = createFile(this.mContext, this.mUri, "vnd.android.document/directory", str);
        if (createFile != null) {
            return new TreeDocumentFile(this, this.mContext, createFile);
        }
        return null;
    }

    public Uri getUri() {
        return this.mUri;
    }

    public String getName() {
        return DocumentsContractApi19.getName(this.mContext, this.mUri);
    }

    public String getType() {
        return DocumentsContractApi19.getType(this.mContext, this.mUri);
    }

    public boolean isDirectory() {
        return DocumentsContractApi19.isDirectory(this.mContext, this.mUri);
    }

    public boolean isFile() {
        return DocumentsContractApi19.isFile(this.mContext, this.mUri);
    }

    public boolean isVirtual() {
        return DocumentsContractApi19.isVirtual(this.mContext, this.mUri);
    }

    public long lastModified() {
        return DocumentsContractApi19.lastModified(this.mContext, this.mUri);
    }

    public long length() {
        return DocumentsContractApi19.length(this.mContext, this.mUri);
    }

    public boolean canRead() {
        return DocumentsContractApi19.canRead(this.mContext, this.mUri);
    }

    public boolean canWrite() {
        return DocumentsContractApi19.canWrite(this.mContext, this.mUri);
    }

    public boolean delete() {
        try {
            return DocumentsContract.deleteDocument(this.mContext.getContentResolver(), this.mUri);
        } catch (Exception e) {
            return false;
        }
    }

    public boolean exists() {
        return DocumentsContractApi19.exists(this.mContext, this.mUri);
    }

    /* JADX WARNING: Removed duplicated region for block: B:15:0x006c A[LOOP:1: B:13:0x0069->B:15:0x006c, LOOP_END] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public android.support.v4.provider.DocumentFile[] listFiles() {
        /*
            r9 = this;
            r6 = 0
            r7 = 0
            android.content.Context r0 = r9.mContext
            android.content.ContentResolver r0 = r0.getContentResolver()
            android.net.Uri r1 = r9.mUri
            android.net.Uri r2 = r9.mUri
            java.lang.String r2 = android.provider.DocumentsContract.getDocumentId(r2)
            android.net.Uri r1 = android.provider.DocumentsContract.buildChildDocumentsUriUsingTree(r1, r2)
            java.util.ArrayList r8 = new java.util.ArrayList
            r8.<init>()
            r2 = 1
            java.lang.String[] r2 = new java.lang.String[r2]     // Catch:{ Exception -> 0x0087, all -> 0x007e }
            r3 = 0
            java.lang.String r4 = "document_id"
            r2[r3] = r4     // Catch:{ Exception -> 0x0087, all -> 0x007e }
            r3 = 0
            r4 = 0
            r5 = 0
            android.database.Cursor r1 = r0.query(r1, r2, r3, r4, r5)     // Catch:{ Exception -> 0x0087, all -> 0x007e }
        L_0x0028:
            boolean r0 = r1.moveToNext()     // Catch:{ Exception -> 0x003d }
            if (r0 == 0) goto L_0x007a
            r0 = 0
            java.lang.String r0 = r1.getString(r0)     // Catch:{ Exception -> 0x003d }
            android.net.Uri r2 = r9.mUri     // Catch:{ Exception -> 0x003d }
            android.net.Uri r0 = android.provider.DocumentsContract.buildDocumentUriUsingTree(r2, r0)     // Catch:{ Exception -> 0x003d }
            r8.add(r0)     // Catch:{ Exception -> 0x003d }
            goto L_0x0028
        L_0x003d:
            r0 = move-exception
        L_0x003e:
            java.lang.String r2 = "DocumentFile"
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ all -> 0x0085 }
            r3.<init>()     // Catch:{ all -> 0x0085 }
            java.lang.String r4 = "Failed query: "
            java.lang.StringBuilder r3 = r3.append(r4)     // Catch:{ all -> 0x0085 }
            java.lang.StringBuilder r0 = r3.append(r0)     // Catch:{ all -> 0x0085 }
            java.lang.String r0 = r0.toString()     // Catch:{ all -> 0x0085 }
            android.util.Log.w(r2, r0)     // Catch:{ all -> 0x0085 }
            closeQuietly(r1)
        L_0x0059:
            int r0 = r8.size()
            android.net.Uri[] r0 = new android.net.Uri[r0]
            java.lang.Object[] r0 = r8.toArray(r0)
            android.net.Uri[] r0 = (android.net.Uri[]) r0
            int r1 = r0.length
            android.support.v4.provider.DocumentFile[] r2 = new android.support.v4.provider.DocumentFile[r1]
            r1 = r6
        L_0x0069:
            int r3 = r0.length
            if (r1 >= r3) goto L_0x0084
            android.support.v4.provider.TreeDocumentFile r3 = new android.support.v4.provider.TreeDocumentFile
            android.content.Context r4 = r9.mContext
            r5 = r0[r1]
            r3.<init>(r9, r4, r5)
            r2[r1] = r3
            int r1 = r1 + 1
            goto L_0x0069
        L_0x007a:
            closeQuietly(r1)
            goto L_0x0059
        L_0x007e:
            r0 = move-exception
            r1 = r7
        L_0x0080:
            closeQuietly(r1)
            throw r0
        L_0x0084:
            return r2
        L_0x0085:
            r0 = move-exception
            goto L_0x0080
        L_0x0087:
            r0 = move-exception
            r1 = r7
            goto L_0x003e
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.v4.provider.TreeDocumentFile.listFiles():android.support.v4.provider.DocumentFile[]");
    }

    private static void closeQuietly(AutoCloseable autoCloseable) {
        if (autoCloseable != null) {
            try {
                autoCloseable.close();
            } catch (RuntimeException e) {
                throw e;
            } catch (Exception e2) {
            }
        }
    }

    public boolean renameTo(String str) {
        try {
            Uri renameDocument = DocumentsContract.renameDocument(this.mContext.getContentResolver(), this.mUri, str);
            if (renameDocument == null) {
                return false;
            }
            this.mUri = renameDocument;
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
