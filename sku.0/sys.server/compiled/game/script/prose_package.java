package script;

import java.io.Serializable;

/**
* variables
*
* Full Names (first + last)
*
* %TU           user
* %TT           target
* %TO           other object
*
* %NU/NT/NO     short name (first name only)
*
* %SU/ST/SO     personal subjective pronoun:    he/she/it
* %OU/OT/OO     personal objective pronoun:     him/her/it
* %PU/PT/PO     possessive pronoun :            his/her/its
* %FU/FT/FO     associated faction
* %RU/RT/RO     associated species
* %HU/HT/HO     honorific title (sir, madame, lady, etc)
* %MU/MT/MO     military rank (if any) (private, commander, captain)
* %KU/KT/KO     title (if any) (master weaponsmith, journeyman chef, medical assistant)
* %DI           digit integer
* %DF           digit float
*
*/

/**
* Provide encapsulation for data involved in a ProsePackage
*/

final public class prose_package implements Serializable
{
	private final static long serialVersionUID = -3334262904982229749L;

	public prose_package ()
	{
		complexGrammar = false;
	}
	public prose_package (boolean _complexGrammar)
	{
		complexGrammar = _complexGrammar;
	}

	/**
	* A participant's data members take the following precedence when displaying:
	*
	* id
	* nameId
	* name
	*
	*/

	final public static class participant_info implements Serializable
	{
		private final static long serialVersionUID = -3334262904982229749L;
		public obj_id    id;
		public string_id nameId;
		public String    name;

		public void set (obj_id _id)
		{
			String    _name   = base_class.getAssignedName (_id);
			string_id _nameId = base_class.getNameStringId  (_id);

			set (_id, _nameId, _name);
		}

		public void set (string_id _nameId)
		{
			set (null, _nameId, null);
		}

		public void set (String _name)
		{
			set (null, null, _name);
		}

		public void set (obj_id _id, string_id _nameId, String _name)
		{
			id     = _id;
			nameId = _nameId;
			name   = _name;
		}
	};

	public string_id stringId            = new string_id ();

	final public participant_info actor  = new participant_info ();
	final public participant_info target = new participant_info ();
	final public participant_info other  = new participant_info ();

	public int       digitInteger;
	public float     digitFloat;
	public boolean   complexGrammar;
}

// Addition from SWG: Prophecy: Prose_Package

public string_id getTOStringId() {
        return other.getStringId();
    }

    public boolean getComplexGrammar() {
        return complexGrammar;
    }

    public string_id getStringId() {
        return stringId;
    }

    public void setDI(int digitInteger) {
        this.digitInteger = digitInteger;
    }

    public void setDF(float digitFloat) {
        this.digitFloat = digitFloat;
    }

    public void setTU(String actor) {
        this.actor.set(actor);
    }

    public void setTU(obj_id actor) {
        this.actor.set(actor);
    }

    public void setTU(string_id actor) {
        this.actor.set(actor);
    }

    public void setTU(String table, String index) {
        setTU(new string_id(table, index));
    }

    public void setTT(String target) {
        this.target.set(target);
    }

    public void setTT(obj_id target) {
        this.target.set(target);
    }

    public void setTT(string_id target) {
        this.target.set(target);
    }

    public void setTT(String table, String index) {
        setTT(new string_id(table, index));
    }

    public void setTO(String other) {
        this.other.set(other);
    }

    public void setTO(obj_id other) {
        this.other.set(other);
    }

    public void setTO(string_id other) {
        this.other.set(other);
    }

    public void setTO(String table, String index) {
        setTO(new string_id(table, index));
    }

    public class participant_info {

        private obj_id id;
        private string_id nameId;
        private String name;

        public void participant_info() {
        }

        public void update(obj_id id) {
            name = base_class.getAssignedName(id);
            nameId = base_class.getNameStringId(id);
            this.id = id;
        }

        public void update(string_id nameId) {
            this.nameId = nameId;
        }

        public void update(String name) {
            this.name = name;
        }

        public obj_id getObjId() {
            return id;
        }

        public string_id getStringId() {
            return nameId;
        }

        public String getString() {
            return name;
        }

        public void set(obj_id id) {
            this.id = id;
        }

        public void set(String name) {
            this.name = name;
        }

        public void set(string_id nameId) {
            this.nameId = nameId;
        }
    }

    /*
		Below are temporary solutions for the syntax errors in our prose_package revamp. We plan to remove these in the near future
     */
    public prose_package(string_id sid, obj_id actor, String actorString, string_id actorStringId, obj_id target, String targetString, string_id targetStringId, obj_id other, String otherString, string_id otherStringId, int di, float df) {
        if (actorString != null && !actorString.isEmpty()) {
            actorStringId = null;
        }
        if (targetString != null && !targetString.isEmpty()) {
            targetStringId = null;
        }
        stringId = sid;
        this.actor.update(actor);
        this.target.update(target);
        this.other.update(other);
        digitInteger = di;
        digitFloat = df;
    }

    public prose_package(string_id sid, obj_id actor, obj_id target, obj_id other, int di) {
        stringId = sid;
        if (actor != null) {
            this.actor.update(actor);
        }
        if (target != null) {
            this.target.update(target);
        }
        if (other != null) {
            this.other.update(other);
        }
        digitInteger = di;
    }

    public prose_package(string_id sid, obj_id actor, obj_id target, obj_id other) {
        stringId = sid;
        this.actor.update(actor);
        this.target.update(target);
        this.other.update(other);
    }

    public prose_package(string_id sid, obj_id actor, obj_id target, string_id other, int di) {
        stringId = sid;
        this.actor.update(actor);
        this.target.update(target);
        this.other.update(other);
        digitInteger = di;
    }

    public prose_package(string_id sid, obj_id actor, obj_id target, string_id other) {
        stringId = sid;
        this.actor.update(actor);
        this.target.update(target);
        this.other.update(other);
    }

    public prose_package(string_id sid, obj_id target, string_id other) {
        stringId = sid;
        this.target.update(target);
        this.other.update(other);
    }

    public prose_package(string_id sid, obj_id actor, obj_id target, String other, int di) {
        stringId = sid;
        this.actor.update(actor);
        if (target != null) {
            this.target.update(target);
        }
        this.other.update(other);
        digitInteger = di;
    }

    public prose_package(string_id sid, obj_id actor, obj_id target, String other) {
        stringId = sid;
        this.actor.update(actor);
        this.target.update(target);
        this.other.update(other);
    }

    public prose_package(string_id sid, obj_id target, String other) {
        stringId = sid;
        this.target.update(target);
        this.other.update(other);
    }

    public prose_package(string_id sid, obj_id actor, obj_id target, int di) {
        stringId = sid;
        this.actor.update(actor);
        this.target.update(target);
        digitInteger = di;
    }

    public prose_package(string_id sid, obj_id actor, obj_id target) {
        stringId = sid;
        this.actor.update(actor);
        this.target.update(target);
    }

    public prose_package(string_id sid, obj_id target) {
        this.target.set(target);
        stringId = sid;
    }

    public prose_package(string_id sid, obj_id target, int di) {
        stringId = sid;
        this.target.update(target);
        digitInteger = di;
    }

    public prose_package(string_id sid, string_id other, int di, float df) {
        stringId = sid;
        this.other.update(other);
        digitInteger = di;
        digitFloat = df;
    }

    public prose_package(string_id sid, string_id other) {
        stringId = sid;
        this.other.update(other);
    }

    public prose_package(string_id sid, string_id target, string_id other, int di) {
        stringId = sid;
        this.target.update(target);
        this.other.update(other);
        digitInteger = di;
    }

    public prose_package(string_id sid, string_id target, string_id other) {
        stringId = sid;
        this.target.update(target);
        this.other.update(other);
    }

    public prose_package(string_id sid, string_id target, String other, int di) {
        stringId = sid;
        this.target.update(target);
        this.other.update(other);
        digitInteger = di;
    }

    public prose_package(string_id sid, string_id target, String other) {
        stringId = sid;
        this.target.update(target);
        this.other.update(other);
    }

    public prose_package(string_id sid, String other, int di, float df) {
        stringId = sid;
        this.other.update(other);
        digitInteger = di;
        digitFloat = df;
    }

    public prose_package(string_id sid, String other) {
        stringId = sid;
        this.other.update(other);
    }

    public prose_package(string_id sid, String other, int di) {
        stringId = sid;
        this.other.update(other);
        digitInteger = di;
    }

    public prose_package(string_id sid, String other, float df) {
        stringId = sid;
        this.other.update(other);
        digitFloat = df;
    }

    public prose_package(string_id sid, int di, float df) {
        stringId = sid;
        digitInteger = di;
        digitFloat = df;
    }

    public prose_package(string_id sid, int di) {
        stringId = sid;
        digitInteger = di;
    }

    public prose_package(string_id sid, float df) {
        stringId = sid;
        digitFloat = df;
    }

    public prose_package(string_id sid, String target, String other) {
        stringId = sid;
        this.target.update(target);
        this.other.update(other);
    }

    public prose_package(string_id sid, String target, String other, int di) {
        stringId = sid;
        this.target.update(target);
        this.other.update(other);
        digitInteger = di;
    }

    public prose_package(string_id sid, String target, String other, String actor) {
        stringId = sid;
        this.target.update(target);
        this.other.update(other);
        this.actor.update(actor);
    }
};

