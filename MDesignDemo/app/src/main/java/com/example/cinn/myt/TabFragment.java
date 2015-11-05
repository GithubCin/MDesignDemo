package com.example.cinn.myt;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.support.design.widget.TabLayout;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.TextView;

import java.util.Random;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link TabFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link TabFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TabFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private TabLayout mTabLayout;
    private ViewPager mViewPager;
    private String[] mTitle = new String[3];
    private String[] mData = new String[3];

    {
        for(int i=0;i<3;i++) {
            mTitle[i] = "title" + (i+1);
            mData[i] = "data" + (i+1);
        }
    }

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment TabFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static TabFragment newInstance(String param1, String param2) {
        TabFragment fragment = new TabFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public TabFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tab, container, false);
        mViewPager = (ViewPager)view.findViewById(R.id.viewpager);
        mTabLayout =(TabLayout) view.findViewById(R.id.tba2);
        mTabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                colorchange(tab.getPosition());
                mViewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        mTabLayout.setTabsFromPagerAdapter(mAdapter);
        final TabLayout.TabLayoutOnPageChangeListener listener =
                new TabLayout.TabLayoutOnPageChangeListener(mTabLayout);
        mViewPager.addOnPageChangeListener(listener);
        mViewPager.setAdapter(mAdapter);
        return view;
    }
    private PagerAdapter mAdapter = new PagerAdapter() {
        @Override
        public CharSequence getPageTitle(int position) {
            return mTitle[position];
        }

        @Override
        public int getCount() {
            return mData.length;
        }

        @Override
        public Object instantiateItem(View container, int position) {
            TextView tv = new TextView(getActivity());
            tv.setTextSize(30.f);
            tv.setText(mData[position]);
            ((ViewPager) container).addView(tv);
            return tv;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            ((ViewPager) container).removeView((View) object);
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }
    };

    private void colorchange(int position){
        //Bitmap bitmap = BitmapFactory.decodeResource(getResources(),
               // SuperAwesomeCardFragment.getBackgroundBitmapPosition(position));
        // Palette的部分

        //Palette.generateAsync(bitmap, new Palette.PaletteAsyncListener() {
            /**
             * 提取完之后的回调方法
             */
            //@Override
//            public void onGenerated(Palette palette) {
//                Palette.Swatch vibrant = palette.getVibrantSwatch();
//            /* 界面颜色UI统一性处理,看起来更Material一些 */
//                mPagerSlidingTabStrip.setBackgroundColor(vibrant.getRgb());
//                mPagerSlidingTabStrip.setTextColor(vibrant.getTitleTextColor());
//                // 其中状态栏、游标、底部导航栏的颜色需要加深一下，也可以不加，具体情况在代码之后说明
//                mPagerSlidingTabStrip.setIndicatorColor(colorBurn(vibrant.getRgb()));
//
//                mToolbar.setBackgroundColor(vibrant.getRgb());
//                if (android.os.Build.VERSION.SDK_INT >= 21) {
//                    Window window = getActivity().getWindow();
//                    window.setStatusBarColor(colorBurn(vibrant.getRgb()));
//                    window.setNavigationBarColor(colorBurn(vibrant.getRgb()));
//                }
//            }
//        });

        //mTabLayout.setBackgroundColor(Color.parseColor("#45c01a"));
        Random rand = new Random();
        int color =  Color.rgb(rand.nextInt(255), rand.nextInt(255), rand.nextInt(255));
       // string strColor = "#"+Convert.ToString(color.ToArgb(),16).PadLeft(8,'0').Substring(2,6);
        mTabLayout.setTabTextColors(Color.parseColor("#000000"), color);
        mTabLayout.setSelectedTabIndicatorColor(color);
        mListener.setToolbarColor(color);
        if (android.os.Build.VERSION.SDK_INT >= 21) {
                    Window window = getActivity().getWindow();
                    // 很明显，这两货是新API才有的。

                    //getActivity().getTheme().resolveAttribute(R.attr.colorPrimaryDark, sb, true);

                    window.setStatusBarColor(colorBurn(color));
                    window.setNavigationBarColor(colorBurn(color));

                }
    }
    private int colorBurn(int RGBValues) {
        int alpha = RGBValues >> 24;
        int red = RGBValues >> 16 & 0xFF;
        int green = RGBValues >> 8 & 0xFF;
        int blue = RGBValues & 0xFF;
        red = (int) Math.floor(red * (1 - 0.1));
        green = (int) Math.floor(green * (1 - 0.1));
        blue = (int) Math.floor(blue * (1 - 0.1));
        return Color.rgb(red, green, blue);
    }


    // TODO: Rename method, update argument and hook method into UI event

    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

      @Override
    public void onAttach(Activity activity) {
         super.onAttach(activity);
         try {
             mListener = (OnFragmentInteractionListener) activity;
         } catch (ClassCastException e) {
             throw new ClassCastException(activity.toString()
                     + " must implement OnFragmentInteractionListener");
         }
     }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
       public void onFragmentInteraction(Uri uri);
        public  void setToolbarColor(int color);

    }

}
