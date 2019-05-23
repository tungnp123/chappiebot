package me.chappiebot.test.data.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Feed {

    public enum SectionType {
        @SerializedName("1")
        TEXT(1),
        @SerializedName("2")
        VIDEO(2),
        @SerializedName("3")
        IMAGE(3);

        private int type;

        SectionType(int type) {
            this.type = type;
        }

        public int getType() {
            return type;
        }
    }

    private String document_id;
    private String title;
    private String description;
    private Image avatar;
    private Publisher publisher;
    private List<Image> images;
    private List<Section> sections;

    public String getDocument_id() {
        return document_id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public Image getAvatar() {
        return avatar;
    }

    public List<Image> getImages() {
        return images;
    }

    public Publisher getPublisher() {
        return publisher;
    }

    public List<Section> getSections() {
        return sections;
    }

    public class Image {
        private String href;
        private String main_color;
        private int width;
        private int height;

        public String getHref() {
            return href;
        }

        public String getMain_color() {
            return main_color;
        }

        public int getWidth() {
            return width;
        }

        public int getHeight() {
            return height;
        }
    }

    public class Publisher {
        private String id;
        private String name;
        private String icon;

        public String getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public String getIcon() {
            return icon;
        }
    }

    public class Section {

        private SectionType section_type;
        private Content content;

        public SectionType getSection_type() {
            return section_type;
        }

        public Content getContent() {
            return content;
        }
    }

    public class Content {
        private String href;
        private String caption;
        private Image preview_image;
        private String text;

        public String getHref() {
            return href;
        }

        public String getCaption() {
            return caption;
        }

        public Image getPreview_image() {
            return preview_image;
        }

        public String getText() {
            return text;
        }
    }
}
