package pojo;

public class Country {

    String xmlId;
    String countryName;
    String heading;
    String headingNote;
    String altHeading;
    String description;

    public String getXmlId() {
        return xmlId;
    }

    public void setXmlId(String xmlId) {
        this.xmlId = xmlId;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public String getHeading() {
        return heading;
    }

    public void setHeading(String heading) {
        this.heading = heading;
    }

    public String getHeadingNote() {
        return headingNote;
    }

    public void setHeadingNote(String headingNote) {
        this.headingNote = headingNote;
    }

    public String getAltHeading() {
        return altHeading;
    }

    public void setAltHeading(String altHeading) {

        if (this.altHeading == null) {
            this.altHeading = altHeading;
        } else {
            this.altHeading = this.altHeading + " " + altHeading;
        }
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
