package dao;

import api.ReceiptResponse;
import generated.tables.Tags;
import generated.tables.records.ReceiptsRecord;
import org.jooq.Configuration;
import org.jooq.DSLContext;
import org.jooq.Record;
import org.jooq.Result;
import org.jooq.impl.DSL;

import java.math.BigDecimal;
import java.util.List;

import static com.google.common.base.Preconditions.checkState;
import static generated.Tables.RECEIPTS;
import static generated.Tables.TAGS;

public class TagDao {
    DSLContext dsl;

    public TagDao(Configuration jooqConfig) {this.dsl = DSL.using(jooqConfig);}

    public String insert(int receipt_id, String tag_category) {
        dsl.insertInto(TAGS, TAGS.RECEIPT_ID, TAGS.TAG_CATEGORY).values(receipt_id, tag_category).execute();
        return "";
    }

    public void delete(int receipt_id) {

        dsl.deleteFrom(TAGS).where(TAGS.RECEIPT_ID.eq(receipt_id)).execute();
    }

    public boolean receiptExist(int receiptID) {
        int id = dsl.select().from(RECEIPTS).where(RECEIPTS.ID.eq(receiptID)).fetchOne(RECEIPTS.ID);
        if (id >= 0) {
            return true;
        } else {
            return false;
        }
    }

    public List<ReceiptsRecord> getReceiptsWithTags() {
        return dsl.select().from(RECEIPTS).join(TAGS).on(RECEIPTS.ID.eq(TAGS.RECEIPT_ID)).fetchInto(RECEIPTS);
    }

    public List<ReceiptsRecord> getReceiptsByTagCat(String tagName) {
        return dsl.select().from(RECEIPTS).join(TAGS).on(RECEIPTS.ID.eq(TAGS.RECEIPT_ID).and(TAGS.TAG_CATEGORY.eq(tagName))).fetchInto(RECEIPTS);
    }
}
