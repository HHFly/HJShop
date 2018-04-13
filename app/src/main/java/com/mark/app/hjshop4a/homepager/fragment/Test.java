//package com.mark.app.hjshop4a.homepager.fragment;
//
//
//        import android.os.Bundle;
//        import android.support.v7.app.AppCompatActivity;
//        import android.support.v7.widget.LinearLayoutManager;
//        import android.support.v7.widget.RecyclerView;
//        import android.util.Log;
//        import android.view.View;
//        import android.view.ViewGroup;
//        import android.widget.TextView;
//
//        import com.mark.app.base.recylerview.IndexPath;
//        import com.mark.app.base.recylerview.MkMultipleSourcesRvAdapter;
//        import com.mark.app.base.recylerview.MkViewHolder;
//
//        import java.util.List;
//
//public class RvDemoActivity extends AppCompatActivity {
//
//    private RecyclerView mRecyclerView;
//    private List<String> mDatas;
//    private RvDemoAdatper mAdapter;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_rv_demo);
//        mRecyclerView = (RecyclerView) findViewById(R.id.rv);
//        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
//        mRecyclerView.setAdapter(mAdapter = new RvDemoAdatper());
//    }
//
//
//    class RvDemoAdatper extends MkMultipleSourcesRvAdapter {
//
//        @Override
//        public int getSectionsCount() {
//            return 100;
//        }
//
//        @Override
//        public int getRowsCountInSection(int section) {
//            switch (section){
//                case 0:return 1000;
//                case 1:return 1000;
//                default:return 1000;
//            }
//        }
//
//        @Override
//        public View onCreateView(ViewGroup parent, int section) {
////            switch (section){
////                case 0:return inflater(parent,R.layout.item_rv_demo);
////                case 1:return inflater(parent,R.layout.item_rv_demo2);
////                default:return inflater(parent,R.layout.item_rv_demo);
////            }
//            return inflater(parent,R.layout.item_rv_demo);
//        }
//
//        @Override
//        public void onBindViewHolder(MkViewHolder holder, IndexPath indexPath) {
//
//            Log.d("tag", "[RvDemoAdatper.onBindViewHolder]:");
//            TextView tv = holder.get(R.id.id_num);
//            tv.setText("section:"+indexPath.section+"|row:"+indexPath.row);
//        }
//    }
//}