package com.xjx.example.view;

import com.xjx.example.controller.*;
import com.xjx.example.entity.*;

import javax.jws.soap.SOAPBinding;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

/**
 * @author 许俊曦
 */
public class UserView {
    private ArticleController articleController = new ArticleController();
    private UserController userController = new UserController();
    private CommentController commentController = new CommentController();
    private LikeController likeController = new LikeController();
    private TagController tagController = new TagController();
    private ColumnController columnController = new ColumnController();
    private FollowingController followingController = new FollowingController();

    // 日期时间格式化器，用于将日期时间对象格式化为指定格式的字符串
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    Scanner scanner = new Scanner(System.in);
    User loginUser = new User();
    public void showUserMenu(User user) {
        this.loginUser = user;
        while (true) {
            System.out.println("---------------------");
            System.out.println("请选择操作：");
            System.out.println("1. 热门文章");
            System.out.println("2. 搜索");
            System.out.println("3. 个人中心");
            System.out.println("4. 退出");
            System.out.println("请输入选项编号: ");
            String choice = scanner.next();
            System.out.println("---------------------");
            switch (choice) {
                case "1":
                    // 查看热门文章
                    showHotArticles(user);
                    break;
                case "2":
                    // 按要求搜索
                    search();
                    break;
                case "3":
                    // 个人中心
                    personalCentre(user);
                    break;
                case "4":
                    // 退出系统
                    System.out.println("退出系统成功！");
                    return;
                default:
                    System.out.println("无效的选项，请重新输入");
            }
        }
    }

    // case 1
    private void showHotArticles(User user){
        List<Article> hotArticles = articleController.getHotArticles();
        System.out.println("----------热门文章列表----------");
        showArticlesAndOperator(user,hotArticles);
    }

    // case 2
    private void search(){
        while (true){
            System.out.println("----------搜索---------");
            System.out.println("1. 文章");
            System.out.println("2. 博主");
            System.out.println("3. 专栏");
            System.out.println("4. 返回");
            System.out.print("请输入选项编号: ");
            String choice = scanner.next();
            switch (choice){
                case "1":
                    searchArticle();
                    break;
                case "2":
                    searchUser();
                    break;
                case "3":
                    searchColumn();
                    break;
                case "4":
                    System.out.println("退出搜索成功！");
                    return;
                default:
                    System.out.println("无效的选项，请重新输入~~");
            }
        }
    }

    private void searchUser() {
        System.out.println("请输入博主的用户名（关键词）：");
        String userKeyword = scanner.next();
        List<User> foundUsers = userController.foundUsers(userKeyword);
        showUsersAndOperator(foundUsers);
    }

    // 展示博主和进行和博主有关的操作
    private void showUsersAndOperator(List<User> users) {
        displayUsers(users);
        if (users.isEmpty()){
            return;
        }
        User user;
        while (true) {
            System.out.println("----------------------");
            System.out.println("1. 查看搜索到的博主");
            System.out.println("2. 关注博主");
            System.out.println("3. 取消关注博主");
            System.out.println("4. 返回主菜单");
            System.out.print("请输入选项编号: ");
            String command = scanner.next();
            switch (command) {
               case "1":
                   user = getValidUser(users);
                   if (user == null){
                       System.out.println("返回成功！");
                       continue;
                   }
                   // 根据user对象获取文章
                   List<Article> foundArticleByUser = articleController.getArticlesByUser(user);
                   System.out.println("=====该博主已发布的文章=====");
                   // 操作
                   showArticlesAndOperator(loginUser,foundArticleByUser);
                   break;
                case "2":
                    // 关注
                    user = getValidUser(users);
                    followUser(user);
                    break;
                case "3":
                    // 取消关注
                    user = getValidUser(users);
                    cancelFollowUser(user);
                    break;
               case "4":
                   System.out.println("返回主菜单成功~~");
                   break;
               default:
                    System.out.println("无效的选项，请重新输入");
            }
            // 输入2时结束外层循环
            if ("4".equals(command)) {
                break;
            }
        }
    }

    private User getValidUser(List<User> users) {
        User user;
        while (true) {
            System.out.println("请确认要查看的博主的用户名（输入0返回）：");
            String username = scanner.next();
            if (username.equals("0")){
                return null;
            }
            // 通过用户名得到user对象
            user = userController.getUserByUsername(username);
            if (user == null){
                System.out.println("该博主不存在，请检查输入！");
                continue;
            }

            // 检查是否是搜索出来的博主
            boolean isSearchUser = false;
            for (User correctUser : users) {
                if (correctUser.getUsername().equals(username)){
                    isSearchUser = true;
                    break;
                }
            }
            if (!isSearchUser){
                System.out.println("这不是你要搜索的博主，请重新输入！");
                continue;
            }
            break;
        }
        return user;
    }

    private void searchColumn() {
        System.out.println("请输入要查看的专栏的名称：");
        String columnName = scanner.next();
        List<Column> columns = columnController.searchColumnsByColumnName(columnName);
        // 进行和栏目有关的操作
        chooseColumnAndColumnArticles(columns);
    }

    private void searchArticle() {
        while (true) {
            System.out.println("------------------");
            System.out.println("1. 按标题和内容搜索");
            System.out.println("2. 按标签搜索");
            System.out.println("3. 返回");
            System.out.print("请输入选项: ");
            String command = scanner.next();
            switch(command){
                case "1":
                    System.out.println("请输入和文章有关的标题或内容（关键词）：");
                    String articleKeyword = scanner.next();
                    List<Article> foundArticlesBykeyword =  articleController.searchArticles(articleKeyword);
                    showArticlesAndOperator(loginUser,foundArticlesBykeyword);
                    break;
                case "2":
                    System.out.println("请输入文章的标签：");
                    String tag = scanner.next();
                    List<Article> getArticlesByTagKeyword = tagController.getArticlesByTagKeyword(tag);
                    showArticlesAndOperator(loginUser,getArticlesByTagKeyword);
                    break;
                case "3":
                    System.out.println("返回成功！");
                    return;
                default:
                    System.out.println("无效的选项，请重新输入~~");
            }
        }
    }


    // case 3
    public void personalCentre(User user){
        while (true){
            System.out.println("---------个人中心--------");
            System.out.println("1. 我的博文");
            System.out.println("2. 发布博文");
            System.out.println("3. 编辑博文");
            System.out.println("4. 删除博文");
            System.out.println("5. 专栏管理");
            System.out.println("6. 我的点赞");
            System.out.println("7. 我的关注");
            System.out.println("8. 返回");
            System.out.print("请输入选项: ");
            String choice = scanner.next();
            switch (choice) {
                case "1":
                    // 我的博文
                    viewMyArticles(user);
                    break;
                case "2":
                    // 发布博文
                    publishArticle(user);
                    break;
                case "3":
                    // 编辑博文
                    editMyArticle(user);
                    break;
                case "4":
                    // 删除博文
                    deleteArticle(user);
                    break;
                case "5":
                    // 专栏管理
                    columnManagement();
                    break;
                case "6":
                    // 我的点赞
                    System.out.println("--------我的点赞--------");
                    List<Article> likeArticles = likeController.getLikeArticlesByUser(loginUser);
                    showArticlesAndOperator(user,likeArticles);
                    break;
                case"7":
                    // 我的关注
                    System.out.println("--------我的关注--------");
                    List<User> followingUsers = followingController.getFollowingUsersByUser(user);
                    showUsersAndOperator(followingUsers);
                    break;
                case "8":
                    // 返回
                    System.out.println("退出个人中心成功！");
                    return;
                default:
                    System.out.println("无效的选项，请重新输入");
            }
        }
    }


    private void viewMyArticles(User user) {
        List<Article> myArticles = articleController.getArticlesByUser(user);
        System.out.println("--------我的博文--------");
        showArticlesAndOperator(user,myArticles);
    }

    public void publishArticle(User user){
        Article article = new Article();
        System.out.println("--------发布博文--------");
        System.out.println("请输入文章的标题：");
        String title = scanner.next();

        System.out.println("请输入文章内容（输入 'END' 结束输入）：");
        StringBuilder contentBuilder = new StringBuilder();
        String line;
        while (true) {
            line = scanner.nextLine();
            if ("END".equals(line)) {
                break;
            }
            contentBuilder.append(line).append("\n");
        }
        String content = contentBuilder.toString();

        article.setTitle(title);
        article.setContent(content);
        article.setAuthor(user);
        if (articleController.publishArticle(article)){
            System.out.println("发布成功！");
        }else {
            System.out.println("发布失败~~");
        }
    }

    private void editMyArticle(User user) {
        while (true) {
            System.out.println("--------编辑博文--------");
            List<Article> myArticles = articleController.getArticlesByUser(user);
            // 打印我的博文的标题
            displayArticlesTitles(myArticles);
            if (myArticles.isEmpty()){
                return;
            }
            // 选择要查看的文章
            int articleId;
            // 确保输入的是int类型
            Article article = null;
            while (true) {
                while (true) {
                    try {
                        System.out.println("请输入要查看的文章ID（输入0返回）：");
                        articleId = scanner.nextInt();
                        scanner.nextLine();
                        break;
                    } catch (Exception e) {
                        System.out.println("您的输入有误，请重写输入！");
                    }
                }
                if (articleId == 0){
                    System.out.println("返回成功！");
                    return;
                }
                // 获取指定文章
                article = articleController.getArticleById(articleId);
                if (article == null){
                    System.out.println("未找到该文章~~");
                    continue;
                }
                printArticleDetails(article);
                break;
            }
            // 编辑文章
            while (true){
                System.out.println("--------------------");
                System.out.println("1. 编辑标题和文章内容");
                System.out.println("2. 添加标签");
                System.out.println("3. 删除标签");
                System.out.println("4. 返回");
                System.out.print("请输入选项编号: ");
                String command = scanner.next();
                switch (command){
                    case "1":
                        System.out.println("请重新设置标题：");
                        String title = scanner.next();

                        System.out.println("请重新输入文章内容（输入 'END' 结束输入）：");
                        StringBuilder contentBuilder = new StringBuilder();
                        String line;
                        while (true) {
                            line = scanner.nextLine();
                            if ("END".equals(line)) {
                                break;
                            }
                            contentBuilder.append(line).append("\n");
                        }
                        String content = contentBuilder.toString();

                        Article updateArticle = new Article();
                        article.setTitle(title);
                        article.setContent(content);

                        if (articleController.editArticle(updateArticle)){
                            System.out.println("编辑成功！");
                        }else {
                            System.out.println("编辑失败");
                        }
                        break;
                    case "2":
                        System.out.println("请输入要添加的标签内容：");
                        String tagName = scanner.next();
                        if (tagController.addTagToArticle(articleId,tagName)) {
                            System.out.println("标签添加成功！");
                        }else {
                            System.out.println("添加失败~~");
                        }
                        break;
                    case "3":
                        List<Tag> tags = tagController.getTagsByArticleId(articleId);
                        if (tags.isEmpty()){
                            System.out.println("当前文章还没有标签");
                        }else {
                            // 展示该文章的标签
                            int i = 1;
                            System.out.println("--------标签列表--------");
                            for (Tag tag : tags) {
                                System.out.println(i++ + ". " + tag.getTagName());
                            }
                            System.out.println("请输入要删除的标签名：");
                            String deletedTagName = scanner.next();
                            if (tagController.deleteTagByName(deletedTagName)){
                                System.out.println("标签删除成功！");
                            }else {
                                System.out.println("删除失败~~");
                            }
                            break;
                        }
                        break;
                    case "4":
                        System.out.println("返回成功！");
                        break;
                    default:
                        System.out.println("无效的选项，请重新输入");
                }
                break;
            }
        }
    }

    public void deleteArticle(User user){
        while (true){
            System.out.println("--------删除博文--------");
            List<Article> myArticles = articleController.getArticlesByUser(user);
            // 展示博文标题
            displayArticlesTitles(myArticles);
            if (myArticles.isEmpty()){
                return;
            }
            System.out.println("1. 查看博文");
            System.out.println("2. 返回");
            System.out.print("请输入选项编号: ");
            String command = scanner.next();
            switch (command){
                case "1":
                    int articleId;
                    // 确保输入的是int类型
                    while (true) {
                        try {
                            System.out.println("请输入要查看的文章ID（输入0返回）：");
                            articleId = scanner.nextInt();
                            break;
                        } catch (Exception e) {
                            System.out.println("您的输入有误，请重写输入！");
                            scanner.nextLine();
                        }
                    }
                    if (articleId == 0){
                        System.out.println("返回成功！");
                        break;
                    }
                    // 查看指定文章
                    Article article = articleController.getArticleById(articleId);
                    if (article == null){
                        System.out.println("未找到该文章，请检查输入！");
                        break;
                    }
                    printArticleDetails(article);
                    // 删除操作
                    System.out.println("1. 删除该文章：" + article.getTitle());
                    System.out.println("2. 返回");
                    System.out.print("请输入选项编号: ");
                    String choice = scanner.next();
                    switch (choice){
                        case "1":
                            if (articleController.deleteArticle(articleId)){
                                System.out.println("删除博文成功！");
                            }else{
                                System.out.println("删除失败~~");
                            }
                            break;
                        case "2":
                            System.out.println("返回成功!");
                            break;
                        default:
                            System.out.println("无效的选项，请重新输入");
                    }
                    break;
                case "2":
                    System.out.println("返回成功！");
                    return;
                default:
                    System.out.println("无效的选项，请重新输入");
            }
        }
    }

    public void columnManagement(){
        while (true) {
            System.out.println("--------专栏管理--------");
            System.out.println("1. 我的专栏");
            System.out.println("2. 新建专栏");
            System.out.println("3. 删除专栏");
            System.out.println("4. 添加我的文章到专栏");
            System.out.println("5. 返回");
            System.out.print("请输入选项编号: ");
            String command = scanner.next();
            switch (command){
                case "1":
                    viewMyColumn();
                    break;
                case "2":
                    addColumn();
                    break;
                case "3":
                    deleteColumn();
                    break;
                case "4":
                    addArticleToColumn();
                    break;
                case "5":
                    System.out.println("返回成功！");
                    return;
                default:
                    System.out.println("无效的选项，请重新输入");
            }
        }

    }

    private void addArticleToColumn() {
        while (true) {
            try {
                List<Article> myArticles = articleController.getArticlesByUser(loginUser);
                System.out.println("--------我的博文--------");
                // 打印所有文章标题
                displayArticlesTitles(myArticles);
                // 添加操作
                System.out.println("请输入要添加到专栏中的文章ID（输入0返回）：");
                String articleID = scanner.next();
                int articleId = Integer.parseInt(articleID);

                if (articleId == 0){
                    return;
                }

                Article article = articleController.getArticleById(articleId);
                // 若未找到文章
                if(article == null){
                    System.out.println("未找到该文章,请检查输入！");
                    continue;
                }

                System.out.println("--------我的专栏--------");
                // 打印所有专栏
                List<Column> myColumn = columnController.getColumnsByUser(loginUser);
                displayColumns(myColumn);
                System.out.println("--------------------------");
                System.out.println("请输入被添加文章的专栏的ID：");
                int columnId = scanner.nextInt();

                // 返回
                if (columnId == 0){
                    return;
                }

                Column column = columnController.getColumnByColumnId(columnId);
                // 若未找到栏目
                if (column == null){
                    System.out.println("未找到该专栏,请检查输入！");
                    continue;
                }

                // 都存在则添加
                if (columnController.addArticleToColumn(columnId,articleId)){
                    System.out.println("文章：" + article.getTitle() + "成功添加到专栏：" + column.getColumnName());
                }else {
                    System.out.println("添加失败~~");
                }
            } catch (NumberFormatException e) {
                System.out.println("输入有误，请输入有效的数字！");
            }
        }
    }


    private void deleteColumn() {
        while (true) {
            // 展示栏目
            List<Column> myColumns = columnController.getColumnsByUser(loginUser);
            displayColumns(myColumns);
            // 选择栏目删除
            int columnId;
            System.out.println("-------------------------------");
            System.out.println("请输入要删除的栏目ID（输入0返回）：");
            try {
                columnId = scanner.nextInt();
                scanner.nextLine();
                // 返回
                if (columnId == 0){
                    return;
                }
                Column column = columnController.getColumnByColumnId(columnId);
                // 若未找到输入的栏目
                if (column == null){
                    System.out.println("未找到该栏目,请检查输入！");
                    continue;
                }
                if (columnController.deleteColumn(columnId)){
                    System.out.println("栏目删除成功！");
                }else {
                    System.out.println("栏目删除失败~~");
                }
                break;
            } catch (Exception e) {
                System.out.println("您的输入有误！请检查");
                scanner.nextLine();
            }
        }
    }

    private void addColumn() {
        System.out.println("-------新建栏目-------");
        System.out.println("请输入栏目的名称：");
        String columnName = scanner.next();
        System.out.println("请输入对栏目的描述");
        String description = scanner.next();
        Column newColumn = new Column();
        newColumn.setColumnName(columnName);
        newColumn.setUser(loginUser);
        newColumn.setDescription(description);
        if (columnController.addColumn(newColumn)){
            System.out.println("新建栏目成功！");
        }else {
            System.out.println("新建栏目失败~~");
        }
    }

    public void viewMyColumn() {
        // 获取我的专栏
        List<Column> myColumns = columnController.getColumnsByUser(loginUser);
        // 若没有专栏
        if (myColumns.isEmpty()){
            System.out.println("--------栏目列表--------");
            System.out.println("暂无专栏");
            return;
        }
        while (true) {
            // 查看栏目
            System.out.println("==============");
            System.out.println("1. 查看专栏");
            System.out.println("2. 返回");
            System.out.println("请输入选项编号：");
            String command = scanner.next();
            switch (command){
                case "1":
                    List<Column> columnsByUser = columnController.getColumnsByUser(loginUser);
                    // 进行和专栏有关的操作
                    chooseColumnAndColumnArticles(columnsByUser);
                    break;
                case "2":
                    System.out.println("返回成功！");
                    return;
                default:
                    System.out.println("无效的选项，请重新输入");
            }
        }

    }

    // 选择查看专栏和专栏文章
    public void chooseColumnAndColumnArticles(List<Column> columns){
        while (true) {

            // 选择专栏的内层循环
            int columnId;
            while (true) {
                try {
                    // 展示专栏
                    displayColumns(columns);
                    if (columns.isEmpty()){
                        return;
                    }
                    // 选择栏目
                    System.out.println("--------------------------------");
                    System.out.println("请确认要查看的专栏ID（输入0返回）：");
                    String columnID = scanner.next();
                    columnId = Integer.parseInt(columnID);
                    // 返回
                    if (columnId == 0){
                        return;
                    }

                    Column column = columnController.getColumnByColumnId(columnId);
                    // 若未找到输入的栏目
                    if (column == null){
                        System.out.println("未找到该专栏,请检查输入！");
                        continue;
                    }
                    // 检查是否是需要的专栏
                    if (isMyColumn(columns, columnId)) continue;
                } catch (NumberFormatException e) {
                    System.out.println("输入有误，请输入有效的数字！");
                    continue;
                }
                break;
            }

            // 选择文章的内层循环
            int articleId;
            while (true) {
                try {
                    // 查看文章列表
                    List<Article> getArticlesByColumn = columnController.getArticlesByColumn(columnId);
                    if (displayArticlesInColumn(getArticlesByColumn)) return;
                    // 选择要查看的文章
                    System.out.println("请输入要查看的文章ID（输入0返回）：");
                    String articleID = scanner.next();
                    articleId = Integer.parseInt(articleID);
                    // 返回
                    if (articleId == 0){
                        return;
                    }

                    // 获取指定文章
                    Article article = articleController.getArticleById(articleId);
                    // 未找到输入的文章
                    if (article == null){
                        System.out.println("未找到该文章,请检查输入！");
                        continue;
                    }

                    // 检查文章是否属于当前专栏
                    boolean isArticleInColumn = false;
                    for (Article columnArticle : getArticlesByColumn) {
                        if (columnArticle.getId() == articleId){
                            isArticleInColumn = true;
                            break;
                        }
                    }
                    if (!isArticleInColumn){
                        System.out.println("该文章不属于当前所选专栏，请重新输入！");
                        continue;
                    }

                    // 操作
                    System.out.println("1. 查看该文章");
                    System.out.println("2. 置顶该文章");
                    System.out.println("3. 取消置顶");
                    System.out.println("4. 返回");
                    String command = scanner.next();
                    switch (command) {
                        case "1":
                            // 展示文章信息
                            printArticleDetails(article);
                            break;
                        case "2":
                            // 置顶文章
                            if (articleController.setArticleTop(articleId,getArticlesByColumn)){
                                System.out.println("置顶成功！");
                            }else {
                                System.out.println("置顶失败");
                            }
                            break;
                        case "3":
                            //取消置顶
                            if (article.isTop()){
                                if (articleController.cancelArticleTop(articleId)){
                                    System.out.println("取消置顶成功！");
                                }else {
                                    System.out.println("取消置顶失败~~");
                                }
                            }else {
                                System.out.println("该文章未置顶~~");
                            }
                            break;
                        case "4":
                            System.out.println("返回成功！");
                            return;
                    }
                } catch (NumberFormatException e) {
                    System.out.println("输入有误，请检查有效的数字！");
                }
            }
        }
    }

    private boolean displayArticlesInColumn(List<Article> getArticlesByColumn) {
        // 如果文章列表为空
        if (getArticlesByColumn.isEmpty()) {
            // 输出提示信息
            System.out.println("没有文章~~");
            return true;
        }
        // 输出文章列表标题
        System.out.println("--------文章标题列表--------");
        // 遍历文章列表
        int i = 1;
        // 展示置顶文章
        System.out.print("当前置顶文章：");
        for (Article isTopArticle : getArticlesByColumn) {
            if (isTopArticle.isTop()){
                System.out.println("文章ID：" + isTopArticle.getId() + "  标题：" + isTopArticle.getTitle());
            }
        }

        // 打印栏目里的其他文章标题
        System.out.println("未置顶文章：");
        for (Article article : getArticlesByColumn) {
            // 已经展示过置顶文章了所以跳过
            if (article.isTop()){
                continue;
            }
            System.out.println(i++ + ". " + "文章ID：" + article.getId() + "  标题：" + article.getTitle());
            // 输出分隔线
            System.out.println("------------------------");
        }
        return false;
    }

    // 检查是否是需要的专栏
    private boolean isMyColumn(List<Column> columns, int columnId) {
        boolean isMyColumn = false;
        for (Column myColumn : columns) {
            if (myColumn.getId() == columnId){
                isMyColumn = true;
                break;
            }
        }
        if (!isMyColumn){
            System.out.println("这不是你的专栏，请重新输入！");
            return true;
        }
        return false;
    }

    public void displayColumns(List<Column> columns){
        if (columns.isEmpty()){
            System.out.println("没有找到专栏~~");
        }else {
            int i = 1;
            System.out.println("------------查看专栏------------");
            for (Column column : columns) {
                System.out.println(i++ + ". " + "栏目名称：" + column.getColumnName() + "  栏目ID：" + column.getId());
                System.out.println("   栏目介绍：" + column.getDescription());
            }
        }
    }

    public void displayArticlesTitles(List<Article> articles){
        // 如果文章列表为空
        if (articles.isEmpty()) {
            // 输出提示信息
            System.out.println("没有文章~~");
            return;
        }

        // 输出文章列表标题
        System.out.println("文章标题列表：");
        // 遍历文章列表
        int i = 1;
        for (Article article : articles) {
            // 打印文章详细信息
            System.out.println(i++ + ". " + "文章ID：" + article.getId() + "  标题：" + article.getTitle());
            // 输出分隔线
            System.out.println("------------------------");
        }
    }

    public void displayArticles(List<Article> articles) {
        // 如果文章列表为空
        if (articles.isEmpty()) {
            // 输出提示信息
            System.out.println("没有找到文章~~");
        } else {
            // 输出文章列表标题
            System.out.println("搜索到以下文章：");
            // 遍历文章列表
            for (Article article : articles) {
                // 打印文章详细信息
                printArticleDetails(article);
                // 输出分隔线
                System.out.println("------------------------");
            }
        }
    }

    // 对文章进行的功能操作
    private void function(int articleId,User user) {
        Article article = articleController.getArticleById(articleId);
        User author = article.getAuthor();
        int userId = user.getId();
        while (true) {
            System.out.println("--------操作--------");
            System.out.println("1. 点赞");
            System.out.println("2. 取消点赞");
            System.out.println("3. 发布评论");
            System.out.println("4. 删除我的评论");
            System.out.println("5. 关注发布该文章的博主");
            System.out.println("6. 取消关注发布该文章的博主");
            System.out.println("7. 添加标签");
            System.out.println("8. 举报文章");
            System.out.println("9. 举报评论");
            System.out.println("10. 返回");
            String choice = scanner.next();
            switch (choice) {
                case "1":
                    if (likeController.addLike(articleId,userId)) {
                        System.out.println("点赞成功！");
                        // 重新获取文章数据
                        article = articleController.getArticleById(articleId);
                    }else {
                        System.out.println("点赞失败~~");
                    }
                    break;
                case "2":
                    if (likeController.cancelLike(articleId,userId)){
                        System.out.println("取消点赞成功！");
                        // 重新获取文章数据
                        article = articleController.getArticleById(articleId);
                    }else {
                        System.out.println("取消点赞失败~~");
                    }
                    break;
                case "3":
                    System.out.println("--------发布评论--------");
                    Comment comment = new Comment();

                    System.out.println("请输入评论（输入 'END' 结束输入）：");
                    StringBuilder contentBuilder = new StringBuilder();
                    String line;
                    while (true) {
                        line = scanner.nextLine();
                        if ("END".equals(line)) {
                            break;
                        }
                        contentBuilder.append(line).append("\n");
                    }
                    String content = contentBuilder.toString();

                    comment.setContent(content);
                    comment.setUser(user);
                    comment.setArticle(article);
                    if (commentController.addComment(comment)){
                        System.out.println("评论发布成功！");
                        // 重新获取文章数据
                        article = articleController.getArticleById(articleId);
                    }else {
                        System.out.println("评论发布失败~~");
                    }
                    break;
                case "4":
                    while (true) {
                        List<Comment> myComments = commentController.viewMyCommentsAndCommentIdByArticle(article,loginUser);
                        // 还未发布评论
                        if (myComments.isEmpty()){
                            System.out.println("您没有发布评论~~");
                            break;
                        }
                        // 已发布评论，选择要删除的评论
                        // 打印我的评论
                        System.out.println("-------------");
                        System.out.println("我的评论：");
                        for (Comment myComment : myComments) {
                            if (myComment.getUser().getId() == loginUser.getId()) {
                                String thisContent = myComment.getContent();
                                // 去除开头的换行符
                                if (thisContent.startsWith("\n")) {
                                    thisContent = thisContent.substring(1);
                                }
                                System.out.println("评论ID：" + myComment.getId() + " 评论内容：" + thisContent);
                            }
                        }
                        while (true) {
                            try {
                                System.out.println("======删除评论======");
                                System.out.println("请输入要删除的评论ID（输入0返回）：");
                                String commentID = scanner.next();
                                int commentId = Integer.parseInt(commentID);

                                if(commentId == 0){
                                    break;
                                }
                                // 若未找到该评论，返回
                                if (commentController.getCommentById(commentId) == null){
                                    System.out.println("未找到该评论，请检查输入");
                                    break;
                                }
                                // 删除评论
                                if (commentController.deleteComment(commentId)) {
                                    System.out.println("该评论删除成功！");
                                } else {
                                    System.out.println("该评论删除失败~~");
                                }
                                break;
                            } catch (NumberFormatException e) {
                                System.out.println("输入有误，请检查！");
                                scanner.nextLine();
                            }
                        }
                        break;
                    }
                    break;
                case "5":
                    // 关注博主
                    followUser(author);
                    break;
                case "6":
                    cancelFollowUser(author);
                    break;
                case "7":
                    System.out.println("--------添加标签--------");
                    System.out.println("请输入要添加的标签内容：");
                    String tagName = scanner.next();
                    if (tagController.addTagToArticle(articleId,tagName)) {
                        System.out.println("标签添加成功！");
                    }else {
                        System.out.println("标签添加失败~~");
                    }
                    break;
                case "8":
                    System.out.println("--------举报该文章--------");
                    Report aricleReport = new Report();
                    System.out.println("请输入举报的理由：");
                    String reason1 = scanner.next();
                    aricleReport.setReporter(user);
                    Article reportedArticle = articleController.getArticleById(articleId);
                    aricleReport.setReportedArticle(reportedArticle);
                    aricleReport.setReason(reason1);
                    if (userController.addReport(aricleReport)){
                        System.out.println("举报成功！");
                    }else {
                        System.out.println("举报失败~~");
                    }
                    break;
                case "9":
                    while (true) {
                        try {
                            System.out.println("--------举报评论--------");
                            Report commentReport = new Report();
                            System.out.println("请输入要举报的评论ID：");
                            String commentID = scanner.next();
                            int commentId = Integer.parseInt(commentID);
                            if (commentController.getCommentById(commentId) == null){
                                System.out.println("该评论不存在，请检查输入！");
                                continue;
                            }
                            System.out.println("请输入举报的理由");
                            String reason2 = scanner.next();
                            commentReport.setReporter(user);
                            Comment reportedComment = commentController.getCommentById(commentId);
                            commentReport.setReportedComment(reportedComment);
                            commentReport.setReason(reason2);
                            if (userController.addReport(commentReport)){
                                System.out.println("举报成功！");
                            }else {
                                System.out.println("举报失败~~");
                            }
                            break;
                        } catch (NumberFormatException e) {
                            e.printStackTrace();
                            System.out.println("输入有误，请输入有效的数字！");
                        }
                    }
                    break;
                case "10":
                    System.out.println("返回成功！");
                    return;
                default:
                    System.out.println("无效的选项，请重新输入~~");
            }
        }
    }

    // 取消关注
    private void cancelFollowUser(User author) {
        // author为null说明上一步要求返回
        if (author == null){
            System.out.println("返回成功！");
            return;
        }
        System.out.println("--------取消关注--------");
        System.out.println("是否取消关注博主：" + author.getUsername());
        System.out.println("是否关注博主(输入Y/N)：" + author.getUsername());
        String choice = scanner.next();
        if (choice.equals("N")){
            System.out.println("返回成功！");
        } else if (choice.equals("Y")) {
            if (followingController.cancelFollowing(loginUser.getId(), author.getId())) {
                System.out.println("取消关注成功！");
            } else {
                System.out.println("取消关注失败~~");
            }
        } else {
            System.out.println("输入有误！请输入Y/N");
        }
    }

    // 关注
    private void followUser(User author) {
        // author为null说明上一步要求返回
        if (author == null){
            System.out.println("返回成功！");
            return;
        }
        System.out.println("--------关注博主--------");
        if (loginUser.getId() == author.getId()){
            System.out.println("不能关注自己哦~~");
            return;
        }
        System.out.println("是否关注博主(输入Y/N)：" + author.getUsername());
        String command = scanner.next();
        if (command.equals("N")){
            System.out.println("返回成功！");
        } else if (command.equals("Y")) {
            Following following = new Following();
            following.setFollowerId(loginUser.getId());
            following.setFollowedId(author.getId());
            if (followingController.addFollowing(following)) {
                System.out.println("关注成功！");
            } else {
                System.out.println("关注失败~~");
            }
        } else {
            System.out.println("输入有误！请输入Y/N");
        }
    }

    public void showArticlesAndOperator(User user,List<Article> articles){
        if (articles.isEmpty()){
            System.out.println("暂无文章");
            return;
        }
        while (true){
            try {
                // 打印所有文章标题
                displayArticlesTitles(articles);
                // 选择要查看的文章
                System.out.println("请输入要查看的文章ID（输入0返回）：");
                String articleID = scanner.next();
                scanner.nextLine();
                int articleId = Integer.parseInt(articleID);

                // 返回
                if (articleId == 0){
                    return;
                }
                // 获取指定文章
                Article article = articleController.getArticleById(articleId);
                // 若未找到输入的文章
                if (article == null){
                    System.out.println("未找到该文章,请检查输入！");
                    continue;
                }

                // 检查文章是否属于当前文章列表
                boolean isCorrectArticle = false;
                for (Article correctArticle : articles) {
                    if (correctArticle.getId() == articleId){
                        isCorrectArticle = true;
                        break;
                    }
                }
                if(!isCorrectArticle){
                    System.out.println("该文章不属于要查看的文章列表，请重新输入！");
                }

                printArticleDetails(article);
                // 功能选择
                function(articleId,user);
                break;
            } catch (NumberFormatException e) {
                System.out.println("输入有误，请检查！");
            }
        }
    }

    public void displayUsers(List<User> users){
        if (users.isEmpty()){
            System.out.println("没有找到博主~~");
        }else {
            System.out.println("搜索到以下博主：");
            int i = 1;
            for (User user : users) {
                // 打印博主的用户名
                System.out.println(i++ + ". " + user.getUsername());
            }
        }
    }
    /**
     * 打印文章详细信息
     * @param article 文章对象
     */
    public void printArticleDetails(Article article) {
        System.out.println("=======文章信息=======");
        // 打印文章的 ID
        System.out.println("ID: " + article.getId());
        // 打印文章的标题
        System.out.println("标题: " + article.getTitle());
        // 打印文章的内容
        System.out.println("内容: " + article.getContent());
        // 评论区
        System.out.println("-------------评论区------------");
        displayComments(article);
        // 打印文章的发布时间，使用日期时间格式化器进行格式化
        System.out.println("发布时间: " + article.getPublishTime().format(DATE_TIME_FORMATTER));
        // 打印文章的点赞数
        System.out.println("点赞数: " + article.getLikeCount());
        // 打印文章的作者
        System.out.println("作者: " + article.getAuthor().getUsername());
    }

    public void displayComments(Article article){
        // 获取文章的评论集合
        List<Comment> comments = commentController.getCommentsByArticle(article);
        if (comments.isEmpty()){
            System.out.println("暂无评论");
            System.out.println();
        }else {
            // 打印我的评论
            int j = 1;
            System.out.print("我的评论：");
            for (Comment comment : comments) {
                if (comment.getUser().getId() == loginUser.getId()){
                    String content = comment.getContent();
                    // 去除开头的换行符
                    if (content.startsWith("\n")) {
                        content = content.substring(1);
                    }
                    System.out.println(j++ + ". " + content);
                }
            }
            int i = 1;
            System.out.println("=======全部评论=======");
            for (Comment comment : comments) {
                // 打印全部评论
                String content = comment.getContent();
                // 去除开头的换行符
                if (content.startsWith("\n")) {
                    content = content.substring(1);
                }
                System.out.println(i++ + ". " + comment.getUser().getUsername() + "：" + content);
            }
            System.out.println("==================");
        }
    }

}
