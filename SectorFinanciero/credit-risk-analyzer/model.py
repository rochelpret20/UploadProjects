from joblib import load

MODEL_PATH = "models/credit_model.pkl"

model = load(MODEL_PATH)

def predict_risk(
    income,
    age,
    debt,
    credit_score
):

    prediction = model.predict(
        [[
            income,
            age,
            debt,
            credit_score
        ]]
    )[0]

    probability = model.predict_proba(
        [[
            income,
            age,
            debt,
            credit_score
        ]]
    )[0]

    return prediction, probability