package com.ben.androiddemo.adapet;

import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.ben.androiddemo.fragment.ItemFragment;

/**
 * Created on 17/4/11.
 *
 * @author Ben
 */


public class ItemsPagerAdapter extends FragmentPagerAdapter {
    private final int PAGE_COUNT = 3;
    private String itemTitles[] = new String[]{"item0", "item1", "item2", "item3", "item4", "item5", "item6", "item7", "item8", "item9", "item10"};
    //        private int[] imageResId = {R.drawable.image1, R.drawable.image2, R.drawable.image3,R.drawable.image1, R.drawable.image2, R.drawable.image3,R.drawable.image1};
    private Context context;

    public ItemsPagerAdapter(FragmentManager fm, Context context) {
        super(fm);
        this.context = context;
    }

    @Override
    public ItemFragment getItem(int position) {
        return ItemFragment.newInstance(position);
    }

    @Override
    public int getCount() {
        return PAGE_COUNT;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        //添加text到tab
        return itemTitles[position];

//            //添加icon到tab  注意tabLayout的style  默认的textAllCaps为true icon无法渲染   改为false
//            Drawable image = context.getResources().getDrawable(imageResId[position]);
//            image.setBounds(0, 0, 90, 90);
//            SpannableString sb = new SpannableString(" ");
//            ImageSpan imageSpan = new ImageSpan(image, ImageSpan.ALIGN_BOTTOM);
//            sb.setSpan(imageSpan, 0, 1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
//            return sb;
//
//            //添加icon和text
//            Drawable image = context.getResources().getDrawable(imageResId[position]);
//            image.setBounds(0, 0, 90, 90); //image.getIntrinsicWidth()
//            SpannableString sb = new SpannableString("  " + itemTitles[position]); //空格用来放image
//            ImageSpan imageSpan = new ImageSpan(image, ImageSpan.ALIGN_BOTTOM);
//            sb.setSpan(imageSpan, 0, 1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
//            return sb;
//
//            //自定义
//            return null;
    }

//        //自定义
//        public View getTabView(int position) {
//            View view = LayoutInflater.from(context).inflate(R.layout.tab_item, null);
//            TextView tv = (TextView) view.findViewById(R.id.textView);
//            tv.setText(itemTitles[position] + "~");
//            ImageView img = (ImageView) view.findViewById(R.id.imageView);
//            img.setImageResource(imageResId[position]);
//            return view;
//        }
}