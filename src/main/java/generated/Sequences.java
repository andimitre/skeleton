/**
 * This class is generated by jOOQ
 */
package generated;


import javax.annotation.Generated;

import org.jooq.Sequence;
import org.jooq.impl.SequenceImpl;


/**
 * Convenience access to all sequences in public
 */
@Generated(
	value = {
		"http://www.jooq.org",
		"jOOQ version:3.7.4"
	},
	comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Sequences {

	/**
	 * The sequence <code>public.system_sequence_13409bb5_46fa_4061_a054_2afca5820fba</code>
	 */
	public static final Sequence<Long> SYSTEM_SEQUENCE_13409BB5_46FA_4061_A054_2AFCA5820FBA = new SequenceImpl<Long>("system_sequence_13409bb5_46fa_4061_a054_2afca5820fba", Public.PUBLIC, org.jooq.impl.SQLDataType.BIGINT);

	/**
	 * The sequence <code>public.system_sequence_5504feb5_a9e1_4c85_bd88_3e57abb2eaf0</code>
	 */
	public static final Sequence<Long> SYSTEM_SEQUENCE_5504FEB5_A9E1_4C85_BD88_3E57ABB2EAF0 = new SequenceImpl<Long>("system_sequence_5504feb5_a9e1_4c85_bd88_3e57abb2eaf0", Public.PUBLIC, org.jooq.impl.SQLDataType.BIGINT);
}
