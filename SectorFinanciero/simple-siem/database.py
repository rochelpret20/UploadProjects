import sqlite3

DATABASE = "db/incidents.db"

def create_database():

    conn = sqlite3.connect(DATABASE)

    cursor = conn.cursor()

    cursor.execute("""
        CREATE TABLE IF NOT EXISTS incidents(
            id INTEGER PRIMARY KEY AUTOINCREMENT,
            ip TEXT,
            username TEXT,
            attempts INTEGER,
            attack_type TEXT
        )
    """)

    conn.commit()
    conn.close()


def save_incident(
    ip,
    username,
    attempts,
    attack_type
):

    conn = sqlite3.connect(DATABASE)

    cursor = conn.cursor()

    cursor.execute("""
        INSERT INTO incidents
        (
            ip,
            username,
            attempts,
            attack_type
        )
        VALUES
        (?, ?, ?, ?)
    """,
    (
        ip,
        username,
        attempts,
        attack_type
    ))

    conn.commit()
    conn.close()