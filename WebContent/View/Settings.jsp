<div class="content">
	<h1>
		<i class="far fa-address-book"></i>
		Impostazioni di ${user.name}
	</h1>
	
	<div id="settings-page">
		<div class="settings-routines">
			<div class="set-info">
				<div class="button button--unshadow button--alternative settings-money">
					<strong>
						Hai ${user.money}&euro; nel portafogli.
					</strong>
					Non puoi ricaricare il tuo saldo perchè questa piattaforma è una demo.</div>
			
				<form onsubmit="return false;" class="psw-form">
					<h2>Cambia la password</h2>
					<input type="password" placeholder="Password attuale">
					<input type="password" placeholder="Nuova password">
					<input type="password" placeholder="Ripeti la password">
					<input class="button button--submit" type="submit">
				</form>
			
				<form onsubmit="return false;" class="email-form">
					<h2>Cambia l'email</h2>
					<input type="email" value="${user.email}">
					<input class="button button--submit" type="submit">
				</form>
			</div>
			
			<div class="set-avatar">
				<div 
					class="avatar"
					style="background-image: url('Static/Assets/${user.avatar}')" 
				></div>
			
				<form onsubmit="return false;" class="avatar-form">
					<h2>Cambia l'avatar</h2>
					<input type="file">
					<input class="button button--submit" type="submit">
				</form>
			</div>
		</div>
	</div>
</div>

<script src="Scripts/SettingsRoutines.js"></script>
