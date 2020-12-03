package client.main;

import client.entity.User;
import client.entity.UserRole;
import client.main.*;
import client.entity.Comment;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class CommentFrame extends SocketJFrame {

    private Integer productId;
    private List<Comment> comments = new ArrayList<>();

    private DefaultListModel model = new DefaultListModel();
    private JPanel mainPanel;
    private JList commentList;
    private JTextArea commentTextArea;
    private JButton deleteButton;
    private JButton sendButton;

    public CommentFrame() {
        setSize(600, 450);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setResizable(false);
        setTitle("Comments");
        setContentPane(mainPanel);
        commentList.setModel(model);
        initButtons();
    }

    private void initButtons(){
        sendButton.addActionListener(e -> {
            String text = commentTextArea.getText();
            commentTextArea.removeAll();
            String query = "command=add_comment&productId=" + productId + "&userId=" + super.getCurrentUser().getId() + "&commentText=" + text;
            String response = super.doRequest(query);

            String[] commentFields = response.split(",");
            Integer id = Integer.parseInt(commentFields[0].replace("id=", ""));
            Integer productId = Integer.parseInt(commentFields[1].replace("productId=", ""));
            Long userId = Long.parseLong(commentFields[2].replace("userId=", ""));
            String commentText = commentFields[3].replace("commentText=", "");
            Comment com = new Comment(id, productId, userId, commentText);
            comments.add(com);
            model.addElement(com.getCommentText());
        });
        deleteButton.addActionListener(e -> {
            int selectedIndex = commentList.getSelectedIndex();
            Comment comment = comments.get(selectedIndex);
            String query = "command=delete_comment&id=" + comment.getId();
            super.doRequest(query);
            comments.remove(selectedIndex);
            model.remove(selectedIndex);
        });
    }

    public void setVisible(Integer productId) {
        this.setVisible(true);
        this.productId = productId;
        fillCommentList();
        User user = super.getCurrentUser();
        if (user.getUserRole() == UserRole.USER) {
            deleteButton.setVisible(false);
            deleteButton.setEnabled(false);
        } else {
            deleteButton.setVisible(true);
            deleteButton.setEnabled(true);
        }
    }

    private void fillCommentList() {
        String query = "command=get_comment&productId=" + productId;
        String response = super.doRequest(query);
        if (!response.equals("result=error") && !response.equals("")) {
            String[] resComments = response.split("&");

            for (String rComment : resComments) {
                String[] commentFields = rComment.split(",");

                Integer id = Integer.parseInt(commentFields[0].replace("id=", ""));
                Integer productId = Integer.parseInt(commentFields[1].replace("productId=", ""));
                Long userId = Long.parseLong(commentFields[2].replace("userId=", ""));
                String commentText = commentFields[3].replace("commentText=", "");
                Comment com = new Comment(id, productId, userId, commentText);
                comments.add(com);
                model.addElement(com.getCommentText());
            }
        }
    }
}
