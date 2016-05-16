package gml.template.androidtemplate.activity;

import android.os.Bundle;

import com.afollestad.materialdialogs.MaterialDialog;
import com.afollestad.materialdialogs.Theme;
import com.joanzapata.pdfview.PDFView;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import gml.template.androidtemplate.R;

/**
 * Created by guomenglong on 16/5/15.
 */
public class ITextActivity extends BaseActivity {
    @Bind(R.id.pdfview)
    PDFView mPdfview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_itext);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.click)
    public void onClick() {
        MaterialDialog materialDialog = new MaterialDialog.Builder(this)
                .customView(R.layout.activity_itext_pdf, true)
                .theme(Theme.LIGHT)
                .positiveText("确定")
                .build();
        PDFView contentView = (PDFView) materialDialog.getCustomView().findViewById(R.id.pdfview);
        contentView.fromAsset("cmp_diagonal_cell.pdf")
                .pages(0, 2, 1, 3, 3, 3)
                .defaultPage(1)
                .showMinimap(false)
                .enableSwipe(true)
                .load();
        materialDialog.show();
        mPdfview.fromAsset("cmp_diagonal_cell.pdf")
                .pages(0, 2, 1, 3, 3, 3)
                .defaultPage(1)
                .showMinimap(false)
                .enableSwipe(true)
                .load();
    }
}
