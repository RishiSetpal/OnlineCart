package com.rishistech.onlinecart;

import android.graphics.Color;
import android.os.Bundle;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class HomeeFragment extends Fragment {

    public HomeeFragment() {
        // Required empty public constructor
    }

    private RecyclerView categoryRecyclerView;
    private CategoryAdapter categoryAdapter;

    //////////// Banner slider
    private ViewPager bannerSlideViewPager;
    private List<SliderModel> sliderModelList;
    private int currentPage = 2;
    private Timer timer;
    final private long DELAYTIME = 3000;
    final private long PERIODTIME = 3000;
    //////////// Banner slider

    //////////// Strip Add
    private ImageView stripAdImage;
    private ConstraintLayout stripAdContainer;
    //////////// Strip Add

    //////////// Horizontal Product
    private TextView horizontalLayoutTitle;
    private Button horizontalLayoutViewAllBtn;
    private RecyclerView horizontalRecyclerView;
    private LinearLayout linearLayout;
    //////////// Horizontal Product

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_homee2, container, false);


        categoryRecyclerView = view.findViewById(R.id.category_recyclerview);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        categoryRecyclerView.setLayoutManager(linearLayoutManager);

        List<CategoryModel> categoryModelList = new ArrayList<>();
        categoryModelList.add(new CategoryModel("link","Home"));
        categoryModelList.add(new CategoryModel("link","Electronics"));
        categoryModelList.add(new CategoryModel("link","Appliances"));
        categoryModelList.add(new CategoryModel("link","Furniture"));
        categoryModelList.add(new CategoryModel("link","Fashion"));
        categoryModelList.add(new CategoryModel("link","Toys"));
        categoryModelList.add(new CategoryModel("link","Sports"));
        categoryModelList.add(new CategoryModel("link","Wall Arts"));
        categoryModelList.add(new CategoryModel("link","Books"));
        categoryModelList.add(new CategoryModel("link","Shoes"));

        categoryAdapter = new CategoryAdapter(categoryModelList);
        categoryRecyclerView.setAdapter(categoryAdapter);
        categoryAdapter.notifyDataSetChanged();

        //////////// Banner slider
        bannerSlideViewPager = view.findViewById(R.id.banner_slider_view_pager);

        sliderModelList = new ArrayList<>();

        sliderModelList.add(new SliderModel(R.drawable.sale_banner5,"#03DAC5"));
        sliderModelList.add(new SliderModel(R.drawable.sale_banner6,"#03DAC5"));

        sliderModelList.add(new SliderModel(R.drawable.sale_banner,"#03DAC5"));
        sliderModelList.add(new SliderModel(R.drawable.sale_banner2,"#03DAC5"));
        sliderModelList.add(new SliderModel(R.drawable.sale_banner3,"#03DAC5"));
        sliderModelList.add(new SliderModel(R.drawable.sale_banner4,"#03DAC5"));
        sliderModelList.add(new SliderModel(R.drawable.sale_banner5,"#03DAC5"));
        sliderModelList.add(new SliderModel(R.drawable.sale_banner6,"#03DAC5"));

        sliderModelList.add(new SliderModel(R.drawable.sale_banner,"#03DAC5"));
        sliderModelList.add(new SliderModel(R.drawable.sale_banner2,"#03DAC5"));

        SliderAdapter sliderAdapter = new SliderAdapter(sliderModelList);
        bannerSlideViewPager.setAdapter(sliderAdapter);
        bannerSlideViewPager.setClipToPadding(false);
        bannerSlideViewPager.setPageMargin(20);

        bannerSlideViewPager.setCurrentItem(currentPage);

        ViewPager.OnPageChangeListener onPageChangeListener = new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                currentPage = position;
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                if (state == ViewPager.SCROLL_STATE_IDLE){
                    pageLooper();
                }
            }
        };
        bannerSlideViewPager.addOnPageChangeListener(onPageChangeListener);

        startBannerSlideShow();
        bannerSlideViewPager.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                pageLooper();
                stopBannerSlideshow();
                if (event.getAction()==MotionEvent.ACTION_UP){
                    startBannerSlideShow();
                }
                return false;
            }
        });
        //////////// Banner slider

        //////////// Strip Add
        stripAdImage = view.findViewById(R.id.strip_ad_image);
        stripAdContainer = view.findViewById(R.id.strip_ad_container);

        stripAdImage.setImageResource(R.drawable.strip_ad);
        stripAdImage.setBackgroundColor(Color.parseColor("#000000"));
        //////////// Strip Add

        //////////// Horizontal Product
        horizontalLayoutTitle = view.findViewById(R.id.horizontal_scroll_layout_title);
        horizontalLayoutViewAllBtn = view.findViewById(R.id.horizontal_scroll_view_all_button);
        horizontalRecyclerView =  view.findViewById(R.id.horizontal_scroll_layout_recyclerview);

        LinearLayoutManager linearLayoutManager1 = new LinearLayoutManager(getContext());
        linearLayoutManager1.setOrientation(LinearLayoutManager.HORIZONTAL);
        horizontalRecyclerView.setLayoutManager(linearLayoutManager1);

        List<HorizontalProductScrollModal> horizontalProductScrollModalList = new ArrayList<>();
        horizontalProductScrollModalList.add(new HorizontalProductScrollModal(R.drawable.mobile5,"IPhone 11Pro","Processor: A10","Rs.80,000/-"));
        horizontalProductScrollModalList.add(new HorizontalProductScrollModal(R.drawable.mobile4,"IPhone 11Pro","Processor: A10","Rs.80,000/-"));
        horizontalProductScrollModalList.add(new HorizontalProductScrollModal(R.drawable.mobile3,"IPhone 11Pro","Processor: A10","Rs.80,000/-"));
        horizontalProductScrollModalList.add(new HorizontalProductScrollModal(R.drawable.mobile2,"IPhone 11Pro","Processor: A10","Rs.80,000/-"));
        horizontalProductScrollModalList.add(new HorizontalProductScrollModal(R.drawable.mobile,"IPhone 11Pro","Processor: A10","Rs.80,000/-"));
        horizontalProductScrollModalList.add(new HorizontalProductScrollModal(R.drawable.mobile2,"IPhone 11Pro","Processor: A10","Rs.80,000/-"));
        horizontalProductScrollModalList.add(new HorizontalProductScrollModal(R.drawable.mobile3,"IPhone 11Pro","Processor: A10","Rs.80,000/-"));
        horizontalProductScrollModalList.add(new HorizontalProductScrollModal(R.drawable.mobile4,"IPhone 11Pro","Processor: A10","Rs.80,000/-"));


        HorizontalProductScrollAdapter horizontalProductScrollAdapter = new HorizontalProductScrollAdapter(horizontalProductScrollModalList);

        horizontalRecyclerView.setAdapter(horizontalProductScrollAdapter);
        horizontalProductScrollAdapter.notifyDataSetChanged();

        //////////// Horizontal Product


        return view;
    }
    //////////// Banner slider
    private void pageLooper(){
         if (currentPage == sliderModelList.size()-2){
             currentPage = 2;
             bannerSlideViewPager.setCurrentItem(currentPage,false);
         }
        if (currentPage == 1){
            currentPage = sliderModelList.size()-3;
            bannerSlideViewPager.setCurrentItem(currentPage,false);
        }
    }
    private void startBannerSlideShow(){
        final Handler handler = new Handler();
        final Runnable update = new Runnable() {
            @Override
            public void run() {
                if (currentPage>=sliderModelList.size()){
                    currentPage=1;
                }
                bannerSlideViewPager.setCurrentItem(currentPage++,true);
            }
        };
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(update);
            }
        },DELAYTIME,PERIODTIME);
    }
    private void stopBannerSlideshow(){
        timer.cancel();
    }
    //////////// Banner slider

}