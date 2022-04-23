public class Main {

    public static void main(String[] args) throws InterruptedException {

        App app = new App();
        app.login("instagramUserName","instagramPassword");
        app.navigateToProfile("zoeisabellakravitz");
        app.clickFirstPost();
        app.likeAllPosts();

    }

}
