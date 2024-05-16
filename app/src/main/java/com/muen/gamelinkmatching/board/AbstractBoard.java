package com.muen.gamelinkmatching.board;

import com.muen.gamelinkmatching.utils.GameConf;
import com.muen.gamelinkmatching.utils.ImageUtil;
import com.muen.gamelinkmatching.view.Piece;
import com.muen.gamelinkmatching.view.PieceImage;

import java.util.List;

/**
 * 游戏区域的抽象类 <br/>
 * <br/>
 * 关于本代码介绍可以参考一下博客: <a href="http://blog.csdn.net/ouyang_peng">欧阳鹏的CSDN博客</a> <br/>
 */

public abstract class AbstractBoard {
    // 定义一个抽象方法, 让子类去实现

    /**
     * 采用模板设计模式：创建一个List集合，该集合里面存放初始化游戏时所需的Piece对象
     */
    protected abstract List<Piece> createPieces(GameConf config, Piece[][] pieces);

    /**
     * 返回Piece数组
     *
     * @param config
     * @return
     */
    public Piece[][] create(GameConf config) {
        // 创建Piece[][]数组
        Piece[][] pieces = new Piece[config.getXSize()][config.getYSize()];
        // 返回非空的Piece集合, 该集合由子类去创建
        List<Piece> notNullPieces = createPieces(config, pieces);
        // 根据非空Piece对象的集合的大小来取图片
        List<PieceImage> playImages = ImageUtil.getPlayImages(config.getContext(), notNullPieces.size());
        // 所有图片的宽、高都是相同的
        int imageWidth = GameConf.PIECE_WIDTH;
        int imageHeight = GameConf.PIECE_HEIGHT;

        // 遍历非空的Piece集合
        for (int i = 0; i < notNullPieces.size(); i++) {
            // 依次获取每个Piece对象
            Piece piece = notNullPieces.get(i);
            piece.setPieceImage(playImages.get(i));
            // 计算每个方块左上角的X、Y座标
            piece.setBeginX(piece.getIndexX() * imageWidth + imageWidth/GameConf.PIECE_X_SUM);
            piece.setBeginY(piece.getIndexY() * imageHeight + config.getBeginImageY());
            // 将该方块对象放入方块数组的相应位置处
            pieces[piece.getIndexX()][piece.getIndexY()] = piece;
        }
        return pieces;
    }
}
