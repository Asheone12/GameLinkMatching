package com.muen.gamelinkmatching.board.impl;


import com.muen.gamelinkmatching.board.AbstractBoard;
import com.muen.gamelinkmatching.utils.GameConf;
import com.muen.gamelinkmatching.view.Piece;

import java.util.ArrayList;
import java.util.List;

/**
 * 创建竖的游戏区域，竖向排列的方块以垂直的空列分隔开<br/>
 * <br/>
 * 关于本代码介绍可以参考一下博客: <a href="http://blog.csdn.net/ouyang_peng">欧阳鹏的CSDN博客</a> <br/>
 */
public class VerticalBoard extends AbstractBoard {
    @Override
    protected List<Piece> createPieces(GameConf config, Piece[][] pieces) {
        // 创建一个Piece集合, 该集合里面存放初始化游戏时所需的Piece对象
        List<Piece> notNullPieces = new ArrayList<Piece>();
        for (int i = 0; i < pieces.length; i++) {
            for (int j = 0; j < pieces[i].length; j++) {
                // 加入判断, 符合一定条件才去构造Piece对象, 并加到集合中
                if (i % 2 == 0) {
                    // 如果x能被2整除, 即单数列不会创建方块
                    // 先构造一个Piece对象, 只设置它在Piece[][]数组中的索引值，
                    // 所需要的PieceImage由其父类负责设置。
                    Piece piece = new Piece(i, j);
                    // 添加到Piece集合中
                    notNullPieces.add(piece);
                }
            }
        }
        return notNullPieces;
    }
}
