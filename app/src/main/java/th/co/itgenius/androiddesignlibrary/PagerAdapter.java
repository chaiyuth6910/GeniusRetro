package th.co.itgenius.androiddesignlibrary;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

public class PagerAdapter extends FragmentStatePagerAdapter {

    int mNumOfTabs;

    public PagerAdapter(FragmentManager fm, int NumOfTabs) {
        super(fm);
        this.mNumOfTabs = NumOfTabs;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case  0:
                HomeFragment tabHome = new HomeFragment();
                return tabHome;
            case 1:
                CourseFragment tabCourse = new CourseFragment();
                return tabCourse;
            case 2:
                ProductFragment tabProduct = new ProductFragment();
                return tabProduct;
            case 3:
                ArticleFragment tabArticle = new ArticleFragment();
                return tabArticle;
            case 4:
                ContactFragment tabContact = new ContactFragment();
                return tabContact;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return mNumOfTabs;
    }
}
