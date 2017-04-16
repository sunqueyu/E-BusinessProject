package com.example.administrator.e_businessproject.view;

import android.view.View;
import android.view.ViewGroup;
import android.view.animation.RotateAnimation;

/**
 * 底部导航工具
 */
public class Tools {

	public static void showView(ViewGroup view) {
		showView(view, 0);
	}

	public static void hiddenView(ViewGroup view) {
		hiddenView(view, 0);

	}

	public static void hiddenView(ViewGroup view, int startOffset) {
		/**
		 * RotateAnimation 中的四个参数：
		 * fromDegrees 从多少度 
		 * toDegrees 到多少度
		 * pivotX 中心点的x坐标
		 * pivotY 中心点的Y坐标
		 */
		RotateAnimation ra = new RotateAnimation(0, 180, view.getWidth() / 2,
				view.getHeight());
		// 设置动画的运行时间
		ra.setDuration(500);
		// 动画执行完后，保持现有的状态
		ra.setFillAfter(true);
		// 设置动画执行的延时时间
		ra.setStartOffset(startOffset);
		// 让view执行动画
		view.startAnimation(ra);
		for (int i = 0; i < view.getChildCount(); i++) {
			View child = view.getChildAt(i);
			child.setVisibility(View.GONE);
		}
	}

	public static void showView(ViewGroup view, int startOffset) {
		RotateAnimation ra = new RotateAnimation(180, 360, view.getWidth() / 2,
				view.getHeight());
		// 设置动画的运行时间
		ra.setDuration(500);
		// 动画执行完后，保持现有的状态
		ra.setFillAfter(true);
		// 设置动画执行的延时时间
		ra.setStartOffset(startOffset);
		// 让view执行动画
		view.startAnimation(ra);
		/**
		 * ViewGroup中的方法介绍：
		 * getChildCount()返回子View的数量
		 * getChildAt(i)获得指定小标的子view
		 */
		for (int i = 0; i < view.getChildCount(); i++) {
			View child = view.getChildAt(i);
			child.setVisibility(View.VISIBLE);
		}
	}

}
