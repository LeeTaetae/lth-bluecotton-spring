package com.app.bluecotton.service;

import com.app.bluecotton.domain.dto.post.*;
import com.app.bluecotton.domain.vo.post.PostCommentVO;
import com.app.bluecotton.domain.vo.post.PostDraftVO;
import com.app.bluecotton.domain.vo.post.PostReplyVO;
import com.app.bluecotton.domain.vo.post.PostVO;
import com.app.bluecotton.repository.PostDAO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
@Transactional(rollbackFor = Exception.class)
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {

    private final PostDAO postDAO;

//    게시판 목록 조회 서비스
    @Override
    public List<PostMainDTO> getPosts(String somCategory, String orderType, Long memberId) {
        return postDAO.findPosts(somCategory, orderType, memberId);
    }

//    게시판 등록 서비스
    @Override
    public void write(PostVO postVO, List<String> imageUrls) {
//        if (postDAO.existsTodayPostInSom(postVO.getMemberId(), postVO.getSomId())) {
//            throw new IllegalStateException("오늘은 이미 해당 솜 카테고리에 게시글을 등록했습니다.");
//        }

        postDAO.insert(postVO);

        if (imageUrls != null && !imageUrls.isEmpty()) {
            boolean isFirst = true;

            for (String url : imageUrls) {
                postDAO.updatePostIdByUrl(url, postVO.getId());

                // ✅ 첫 번째 이미지일 때만 대표 썸네일로 설정
                if (isFirst) {
                    postDAO.insertThumbnail(url, postVO.getId());
                    isFirst = false;
                }
            }
        } else {
            // ✅ 이미지가 없으면 기본 이미지 등록
            postDAO.insertDefaultImage("/upload/default/", "default_post.jpg", postVO.getId());
        }
    }

    @Override
    public List<SomCategoryDTO> getJoinedCategories(Long memberId) {
        return postDAO.findJoinedCategories(memberId);
    }

//    게시판 삭제 서비스
    @Override
    public void withdraw(Long postId) {
        postDAO.deleteLikesByPostId(postId);
        postDAO.deleteReportsByPostId(postId);
        postDAO.deletePostImages(postId);
        postDAO.deleteRecentsByPostId(postId);

        postDAO.deletePostById(postId);
    }

//    임시저장 등록 서비스
    @Override
    public void registerDraft(PostDraftVO postDraftVO) {
        postDAO.insertDraft(postDraftVO);
    }

//    수정 게시판 조회 서비스
    @Override
    public PostModifyDTO getPostForUpdate(Long id) {
        Long memberId = 1L;
        return postDAO.findByIdForUpdate(id);
    }

//    게시판 수정 서비스
    @Override
    public void modifyPost(PostVO postVO) {
        postDAO.update(postVO);
    }

//    게시판 조회 서비스
    @Override
    public PostDetailDTO getPostDetail(Long postId) {

        // 조회수 + 1
        postDAO.updateReadCount(postId);

        // 최근 본 게시물 추가
        Long memberId = 1L;
        postDAO.registerRecent(memberId, postId);

        //  게시글 상세 내용 조회
        PostDetailDTO post = postDAO.findPostDetailById(postId);
        List<PostCommentDTO> comments = postDAO.findPostCommentsByPostId(postId);

        // 각 댓글에 대댓글 매핑
        for (PostCommentDTO comment : comments) {
            List<PostReplyDTO> replies = postDAO.findPostRepliesByPostId(comment.getCommentId());
            comment.setReplies(replies);
        }

        post.setComments(comments);
        return post;
    }

//    댓글 등록 서비스
    @Override
    public void insertComment(PostCommentVO postCommentVO) {
        postDAO.insertComment(postCommentVO);
    }


//    답글 등록 서비스
    @Override
    public void insertReply(PostReplyVO postReplyVO) {
        postDAO.insertReply(postReplyVO);
    }

//    댓글 삭제 서비스
    @Override
    public void deleteComment(Long commentId) {
        postDAO.deleteComment(commentId);
    }

//    답글 삭제 서비스
    @Override
    public void deleteReply(Long replyId) {
        postDAO.deleteReply(replyId);
    }

//    개시글 좋아요 서비스
    @Override
    public void toggleLike(Long postId, Long memberId) {
        if (postDAO.existsLike(postId, memberId)) {
            postDAO.deleteLike(postId, memberId); // 좋아요 취소
        } else {
            postDAO.insertLike(postId, memberId); // 좋아요 추가
        }
    }
}
